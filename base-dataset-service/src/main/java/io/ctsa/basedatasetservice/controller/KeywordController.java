package io.ctsa.basedatasetservice.controller;

import io.ctsa.basedatasetservice.service.KeywordService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.OK;

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
}


@Data
@NoArgsConstructor
@AllArgsConstructor
class SynonymsWrapper {

    private int keywordId;
    List<String> synonyms;
}
