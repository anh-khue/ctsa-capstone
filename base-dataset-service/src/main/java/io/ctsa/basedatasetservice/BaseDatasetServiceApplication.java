package io.ctsa.basedatasetservice;

import io.ctsa.basedatasetservice.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BaseDatasetServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseDatasetServiceApplication.class, args);
    }

    @Autowired
    @Bean
    CommandLineRunner runner(KeywordService keywordService) {
        return args -> keywordService.pushToElasticsearch();
    }
}
