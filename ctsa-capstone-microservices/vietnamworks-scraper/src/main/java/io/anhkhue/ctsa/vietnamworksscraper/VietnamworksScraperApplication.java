package io.anhkhue.ctsa.vietnamworksscraper;

import io.anhkhue.ctsa.vietnamworksscraper.scraper.collector.VietnamworksCollector;
import io.anhkhue.ctsa.vietnamworksscraper.scraper.persistence.DataPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
public class VietnamworksScraperApplication {

    public static void main(String[] args) {
        SpringApplication.run(VietnamworksScraperApplication.class, args);
    }

    @Autowired
    @Bean
    CommandLineRunner runner(VietnamworksCollector vietnamworksCollector,
                             DataPersistence dataPersistence) {
        return args -> vietnamworksCollector.collectData()
                                            .forEach(dataPersistence::persist);
    }
}
