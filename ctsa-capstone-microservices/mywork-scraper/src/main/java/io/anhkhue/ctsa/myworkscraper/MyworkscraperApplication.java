package io.anhkhue.ctsa.myworkscraper;

import io.anhkhue.ctsa.myworkscraper.scraper.collector.CollectedDataModel;
import io.anhkhue.ctsa.myworkscraper.scraper.collector.MyworkCollector;
import io.anhkhue.ctsa.myworkscraper.scraper.persistence.DataPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@SpringBootApplication
public class MyworkscraperApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyworkscraperApplication.class, args);
    }

    @Autowired
    @Bean
    CommandLineRunner runner(MyworkCollector myworkCollector,
                             DataPersistence dataPersistence) {
        return args -> {
            List<CollectedDataModel> collectedData = myworkCollector.collectData()
                    .stream()
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            System.out.println(collectedData.size());

            /*collectedData.forEach(data -> {
                System.out.println(data.getPostedDate());
                System.out.println(data.getPosition());
                data.getSkills().forEach(System.out::println);
            });*/

            collectedData.forEach(dataPersistence::persist);
        };
    }
}
