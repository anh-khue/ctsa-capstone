package io.ctsa.careerssuggestionsservice;

import io.ctsa.careerssuggestionsservice.suggestion.cluster.MajorCluster;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@EnableEurekaClient
@SpringBootApplication
public class CareersSuggestionsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CareersSuggestionsServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(MajorCluster majorCluster) {
        return args -> {

        };
//        Arrays.asList(1, 4, 5).forEach(majorCluster::findCentroid);
    }
}