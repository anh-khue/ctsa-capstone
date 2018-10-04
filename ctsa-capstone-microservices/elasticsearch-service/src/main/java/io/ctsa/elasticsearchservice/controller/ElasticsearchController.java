package io.ctsa.elasticsearchservice.controller;

import io.ctsa.elasticsearchservice.communicator.elasticsearch.ElasticsearchWebClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/elasticsearch")
public class ElasticsearchController {

    private final ElasticsearchWebClient elasticsearchWebClient;

    public ElasticsearchController(ElasticsearchWebClient elasticsearchWebClient) {
        this.elasticsearchWebClient = elasticsearchWebClient;
    }

    @PostMapping("/_extract/keywords/json")
    public ResponseEntity extractKeywordsJson(@RequestBody String content) {
        return ResponseEntity.status(OK)
                             .body(elasticsearchWebClient.extractKeywords(content));
    }

    @PostMapping("/_extract/keywords")
    public ResponseEntity extractKeywords(@RequestBody String content) {
        return ResponseEntity.status(OK)
                             .body(elasticsearchWebClient.retrieveHitKeywords(content));
    }
}
