package io.ctsa.careersservice.controller;

import io.ctsa.careersservice.exception.NotFoundInDatasetException;
import io.ctsa.careersservice.model.Keyword;
import io.ctsa.careersservice.service.KeywordService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@CrossOrigin(origins = "*")
@RestController
public class KeywordController {

    private final KeywordService keywordService;

    public KeywordController(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    @GetMapping("/keywords")
    public ResponseEntity getKeywords(@RequestParam("item-per-page") int itemPerPage,
                                      @RequestParam("page") int page) {
        Page<Keyword> keywordsPage = keywordService.getKeywordsByPage(itemPerPage, page);
        return ResponseEntity.status(OK)
                             .body(keywordsPage.getContent());
    }

    @GetMapping("/keywords/pages-count")
    public ResponseEntity getTotalPage(@RequestParam("item-per-page") int itemPerPage) {
        Page<Keyword> keywordsPage = keywordService.getKeywordsByPage(itemPerPage, 1);
        return ResponseEntity.status(OK)
                             .body(keywordsPage.getTotalPages());
    }

    @PostMapping("/keywords")
    public ResponseEntity insertSynonyms(@RequestBody SynonymsWrapper synonymsWrapper) {
        try {
            keywordService.insertSynonyms(synonymsWrapper.getSynonyms(),
                                          synonymsWrapper.getKeywordId());
            return ResponseEntity.status(OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(CONFLICT)
                                 .body("Keyword already existed.");
        }
    }

    @GetMapping("/keywords/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        try {
            return ResponseEntity.status(OK)
                                 .body(keywordService.findById(Integer.parseInt(id))
                                                     .orElseThrow(NotFoundInDatasetException::new));
        } catch (NotFoundInDatasetException e) {
            return ResponseEntity.status(NOT_FOUND).build();
        }
    }

    @PostMapping("/keywords/word")
    public ResponseEntity getByWord(@RequestBody String word) {
        try {
            return ResponseEntity.status(OK)
                                 .body(keywordService.findByWord(word)
                                                     .orElseThrow(NotFoundInDatasetException::new));
        } catch (NotFoundInDatasetException e) {
            return ResponseEntity.status(NOT_FOUND).build();
        }
    }

    @GetMapping("/keywords/elasticsearch={status}")
    public ResponseEntity getByPushedToElasticsearch(@PathVariable String status) {
        return ResponseEntity.status(OK)
                             .body(keywordService.getByPushedToElasticsearch(Boolean.parseBoolean(status)));
    }

    @PutMapping("/keywords/elasticsearch")
    public ResponseEntity pushToElasticsearch(@RequestBody List<String> words) {
        words.forEach(keywordService::pushToElasticsearch);
        return ResponseEntity.status(OK)
                             .build();
    }
}


@Data
@NoArgsConstructor
@AllArgsConstructor
class SynonymsWrapper {

    private int keywordId;
    List<String> synonyms;
}
