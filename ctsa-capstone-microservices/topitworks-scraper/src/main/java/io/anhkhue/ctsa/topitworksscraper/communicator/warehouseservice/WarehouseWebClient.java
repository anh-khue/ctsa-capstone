package io.anhkhue.ctsa.topitworksscraper.communicator.warehouseservice;

import io.anhkhue.ctsa.topitworksscraper.communicator.Communicator;
import io.anhkhue.ctsa.topitworksscraper.model.Recruitment;
import io.anhkhue.ctsa.topitworksscraper.model.RequiredSkill;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WarehouseWebClient {

    private final WebClient webClient;

    public WarehouseWebClient(Communicator warehouseCommunicator) {
        webClient = warehouseCommunicator.client();
    }

    public Recruitment saveRecruitment(Recruitment recruitment) {
        return webClient.post()
                        .uri("/recruitment")
                        .body(BodyInserters.fromObject(recruitment))
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(Recruitment.class)
                        .block();
    }

    public void saveRequiredSkill(RequiredSkill requiredSkill) {
        webClient.post()
                 .uri("/required_skills")
                 .body(BodyInserters.fromObject(requiredSkill))
                 .accept(MediaType.APPLICATION_JSON)
                 .retrieve()
                 .bodyToMono(RequiredSkill.class)
                 .block();
    }
}
