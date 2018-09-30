package io.ctsa.basedatasetservice.controller;

import io.ctsa.basedatasetservice.exception.NotFoundInDatasetException;
import io.ctsa.basedatasetservice.service.KeywordService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/keywords")
public class KeywordController {

    private final KeywordService keywordService;

    public KeywordController(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    @GetMapping
    public ResponseEntity getKeywordsMap() {
        return ResponseEntity.status(OK)
                             .body(keywordService.getKeywordsMap());
    }

    @PostMapping
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

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        try {
            return ResponseEntity.status(OK)
                                 .body(keywordService.findById(Integer.parseInt(id))
                                                     .orElseThrow(NotFoundInDatasetException::new));
        } catch (NotFoundInDatasetException e) {
            return ResponseEntity.status(NOT_FOUND).build();
        }
    }

    @PostMapping("/word")
    public ResponseEntity getByWord(@RequestBody String word) {
        try {
            return ResponseEntity.status(OK)
                                 .body(keywordService.findByWord(word)
                                                     .orElseThrow(NotFoundInDatasetException::new));
        } catch (NotFoundInDatasetException e) {
            return ResponseEntity.status(NOT_FOUND).build();
        }
    }
}


@Data
@NoArgsConstructor
@AllArgsConstructor
class SynonymsWrapper {

    private int keywordId;
    List<String> synonyms;
}
