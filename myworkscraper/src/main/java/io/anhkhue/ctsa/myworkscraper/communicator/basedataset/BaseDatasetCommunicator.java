package io.anhkhue.ctsa.myworkscraper.communicator.basedataset;

import io.anhkhue.ctsa.myworkscraper.communicator.Communicator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class BaseDatasetCommunicator implements Communicator {

    @Value("http://localhost:8001")
    private String baseUrl;

    @Override
    public WebClient client() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(baseUrl)
                .build();
    }
}
