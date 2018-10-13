package io.anhkhue.ctsa.vietnamworksscraper.communicator.careersservice;

import io.anhkhue.ctsa.vietnamworksscraper.communicator.Communicator;
import io.anhkhue.ctsa.vietnamworksscraper.exception.NoKeywordFoundException;
import io.anhkhue.ctsa.vietnamworksscraper.exception.NoPositionFoundException;
import io.anhkhue.ctsa.vietnamworksscraper.exception.NoSkillFoundException;
import io.anhkhue.ctsa.vietnamworksscraper.model.Keyword;
import io.anhkhue.ctsa.vietnamworksscraper.model.Position;
import io.anhkhue.ctsa.vietnamworksscraper.model.Skill;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CareersServiceWebClient {

    private final WebClient webClient;

    public CareersServiceWebClient(Communicator careersServiceCommunicator) {
        this.webClient = careersServiceCommunicator.client();
    }

    public Keyword getKeywordById(int id) {
        return this.webClient.get()
                             .uri("/keywords/" + id)
                             .accept(MediaType.APPLICATION_JSON)
                             .retrieve()
                             .onStatus(HttpStatus::is4xxClientError,
                                       clientResponse -> Mono.error(new NoKeywordFoundException()))
                             .bodyToMono(Keyword.class)
                             .block();
    }

    public Keyword getKeywordByWord(String word) {
        return this.webClient.post()
                             .uri("/keywords/word")
                             .body(BodyInserters.fromObject(word))
                             .accept(MediaType.APPLICATION_JSON)
                             .retrieve()
                             .onStatus(HttpStatus::is4xxClientError,
                                       clientResponse -> Mono.error(new NoKeywordFoundException()))
                             .bodyToMono(Keyword.class)
                             .block();
    }

    public Position getPositionById(int id) {
        return this.webClient.get()
                             .uri("/positions/" + id)
                             .accept(MediaType.APPLICATION_JSON)
                             .retrieve()
                             .onStatus(HttpStatus::is4xxClientError,
                                       clientResponse -> Mono.error(new NoPositionFoundException()))
                             .bodyToMono(Position.class)
                             .block();
    }

    public Skill getSkillById(int id) {
        return this.webClient.get()
                             .uri("/skills/" + id)
                             .accept(MediaType.APPLICATION_JSON)
                             .retrieve()
                             .onStatus(HttpStatus::is4xxClientError,
                                       clientResponse -> Mono.error(new NoSkillFoundException()))
                             .bodyToMono(Skill.class)
                             .block();
    }
}
