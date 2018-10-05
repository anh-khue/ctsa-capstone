package io.anhkhue.ctsa.myworkscraper.communicator;

import org.springframework.web.reactive.function.client.WebClient;

public interface Communicator {

    WebClient client();
}
