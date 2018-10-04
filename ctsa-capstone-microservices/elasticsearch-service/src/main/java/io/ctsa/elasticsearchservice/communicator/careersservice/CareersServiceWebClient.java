package io.ctsa.elasticsearchservice.communicator.careersservice;

import io.ctsa.elasticsearchservice.communicator.Communicator;
import io.ctsa.elasticsearchservice.exception.NoKeywordFoundException;
import io.ctsa.elasticsearchservice.model.Keyword;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class CareersServiceWebClient {

    private final WebClient webClient;

    public CareersServiceWebClient(Communicator careersServiceCommunicator) {
        this.webClient = careersServiceCommunicator.client();
    }

    public List<Keyword> getNotYetPushedKeywords() {
        return this.webClient.get()
                             .uri("/keywords/elasticsearch=false")
                             .accept(MediaType.APPLICATION_JSON)
                             .retrieve()
                             .onStatus(HttpStatus::is4xxClientError,
                                       clientResponse -> Mono.error(new NoKeywordFoundException()))
                             .bodyToFlux(Keyword.class)
                             .collectList()
                             .block();
    }

    public void informPushedToElasticsearch(List<String> keywords) {
        this.webClient.put()
                      .uri("/keywords/elasticsearch")
                      .body(BodyInserters.fromObject(keywords))
                      .accept(MediaType.APPLICATION_JSON)
                      .retrieve()
                      .bodyToMono(String.class).block();
    }
}
