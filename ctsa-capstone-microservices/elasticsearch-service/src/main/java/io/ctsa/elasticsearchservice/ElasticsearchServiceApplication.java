package io.ctsa.elasticsearchservice;

import io.ctsa.elasticsearchservice.service.ElasticsearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ElasticsearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticsearchServiceApplication.class, args);
	}

    @Bean
    @Autowired
    CommandLineRunner runner(ElasticsearchService elasticsearchService) {
        return args -> elasticsearchService.pushKeywordsToElasticsearch();
    }
}
