package io.anhkhue.ctsa.myworkscraper.communicator.basedataset;

import io.anhkhue.ctsa.myworkscraper.communicator.Communicator;
import io.anhkhue.ctsa.myworkscraper.exception.NoKeywordFoundException;
import io.anhkhue.ctsa.myworkscraper.exception.NoPositionFoundException;
import io.anhkhue.ctsa.myworkscraper.exception.NoSkillFoundException;
import io.anhkhue.ctsa.myworkscraper.model.Keyword;
import io.anhkhue.ctsa.myworkscraper.model.Position;
import io.anhkhue.ctsa.myworkscraper.model.Skill;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class BaseDatasetWebClient {

    private final WebClient webClient;

    public BaseDatasetWebClient(Communicator baseDatasetCommunicator) {
        this.webClient = baseDatasetCommunicator.client();
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
