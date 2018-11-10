package io.ctsa.resultssuggestionsservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
public class ResultsSuggestionsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResultsSuggestionsServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
        };
    }
}