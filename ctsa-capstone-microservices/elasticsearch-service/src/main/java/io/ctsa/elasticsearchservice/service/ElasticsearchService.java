package io.ctsa.elasticsearchservice.service;

import io.ctsa.elasticsearchservice.communicator.careersservice.CareersServiceWebClient;
import io.ctsa.elasticsearchservice.communicator.elasticsearch.ElasticsearchWebClient;
import io.ctsa.elasticsearchservice.model.Keyword;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElasticsearchService {

    private final CareersServiceWebClient careersServiceWebClient;
    private final ElasticsearchWebClient elasticsearchWebClient;

    public ElasticsearchService(CareersServiceWebClient careersServiceWebClient,
                                ElasticsearchWebClient elasticsearchWebClient) {
        this.careersServiceWebClient = careersServiceWebClient;
        this.elasticsearchWebClient = elasticsearchWebClient;
    }

    public void pushKeywordsToElasticsearch() {
        List<Keyword> keywords = careersServiceWebClient.getNotYetPushedKeywords();
        elasticsearchWebClient.pushKeywords(keywords);
        careersServiceWebClient.informPushedToElasticsearch(keywords.stream()
                                                                    .map(Keyword::getWord)
                                                                    .collect(Collectors.toList()));
    }
}
