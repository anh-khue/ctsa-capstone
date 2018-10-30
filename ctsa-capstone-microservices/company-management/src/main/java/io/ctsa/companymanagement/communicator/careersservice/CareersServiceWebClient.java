package io.ctsa.companymanagement.communicator.careersservice;

import io.ctsa.companymanagement.exception.NoKeywordFoundException;
import io.ctsa.companymanagement.exception.NoSkillFoundException;
import io.ctsa.companymanagement.model.Skill;
import javassist.compiler.ast.Keyword;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CareersServiceWebClient {

    private final WebClient webClient;

    public CareersServiceWebClient(CareersServiceCommunicator communicator) {
        this.webClient = communicator.client();
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

//    public Position getPositionById(int id) {
//        return this.webClient.get()
//                .uri("/positions/" + id)
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .onStatus(HttpStatus::is4xxClientError,
//                        clientResponse -> Mono.error(new NoPositionFoundException()))
//                .bodyToMono(Position.class)
//                .block();
//    }
}
