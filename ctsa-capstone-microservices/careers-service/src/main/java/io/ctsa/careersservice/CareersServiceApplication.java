package io.ctsa.careersservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CareersServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CareersServiceApplication.class, args);
    }
}
