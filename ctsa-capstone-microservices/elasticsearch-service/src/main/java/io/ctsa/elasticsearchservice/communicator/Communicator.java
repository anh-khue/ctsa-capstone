package io.ctsa.elasticsearchservice.communicator;

import org.springframework.web.reactive.function.client.WebClient;

public interface Communicator {

    WebClient client();
}
