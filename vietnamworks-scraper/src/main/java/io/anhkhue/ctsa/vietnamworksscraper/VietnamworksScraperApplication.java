package io.anhkhue.ctsa.vietnamworksscraper;

import io.anhkhue.ctsa.vietnamworksscraper.scraper.collector.CollectedDataModel;
import io.anhkhue.ctsa.vietnamworksscraper.scraper.collector.Collector;
import io.anhkhue.ctsa.vietnamworksscraper.scraper.collector.VietnamworksCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VietnamworksScraperApplication {

    public static void main(String[] args) {
        SpringApplication.run(VietnamworksScraperApplication.class, args);
    }

    @Autowired
    @Bean
    CommandLineRunner runner(VietnamworksCollector vietnamworksCollector) {
        return args -> {
            List<CollectedDataModel> collectedData = vietnamworksCollector.collectData();

            collectedData.forEach(data -> {
                System.out.println(data.getPostedDate());
                System.out.println(data.getPosition());
                data.getSkills().forEach(System.out::println);
            });
        };
    }
}
