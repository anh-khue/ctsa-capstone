package io.ctsa.elasticsearchservice.communicator.elasticsearch;

import io.ctsa.elasticsearchservice.communicator.Communicator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ElasticsearchCommunicator implements Communicator {

    @Value("${ctsa.elasticsearch.base-url}")
    private String baseUrl;

    @Override
    public WebClient client() {
        return WebClient.builder()
                        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .baseUrl(baseUrl)
                        .build();
    }
}
