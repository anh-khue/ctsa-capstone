package io.anhkhue.ctsa.vietnamworksscraper.communicator;

import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

public interface Communicator {

    WebClient client();
}
