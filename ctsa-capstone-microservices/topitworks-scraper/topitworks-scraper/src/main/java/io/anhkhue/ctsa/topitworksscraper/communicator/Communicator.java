package io.anhkhue.ctsa.topitworksscraper.communicator;

import org.springframework.web.reactive.function.client.WebClient;

public interface Communicator {

    WebClient client();
}
