package io.anhkhue.ctsa.vietnamworksscraper.communicator.elasticsearch;

import io.anhkhue.ctsa.vietnamworksscraper.communicator.Communicator;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class ElasticsearchWebClient {

    private final WebClient webClient;

    public ElasticsearchWebClient(Communicator elasticsearchCommunicator) {
        webClient = elasticsearchCommunicator.client();
    }

    public List<String> extractKeywords(String content) {
        String response = webClient.post()
                                   .uri("/elasticsearch/_extract/keywords/json")
                                   .body(BodyInserters.fromObject(content))
                                   .accept(MediaType.APPLICATION_JSON)
                                   .retrieve()
                                   .bodyToMono(String.class)
                                   .block();

        return webClient.post()
                        .uri("/elasticsearch/_extract/keywords")
                        .body(BodyInserters.fromObject(response != null ? response : ""))
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(List.class)
                        .block();
    }
}
