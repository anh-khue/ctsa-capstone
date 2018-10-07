package io.anhkhue.ctsa.myworkscraper;

import io.anhkhue.ctsa.myworkscraper.scraper.collector.CollectedDataModel;
import io.anhkhue.ctsa.myworkscraper.scraper.collector.MyWorkCollector;
import io.anhkhue.ctsa.myworkscraper.scraper.persistence.DataPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MyworkscraperApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyworkscraperApplication.class, args);
    }

    @Autowired
    @Bean
    CommandLineRunner runner(MyWorkCollector myworkCollector,
                             DataPersistence dataPersistence) {
        return args -> {
            List<CollectedDataModel> collectedData = myworkCollector.collectData();

            /*collectedData.forEach(data -> {
                System.out.println(data.getPostedDate());
                System.out.println(data.getPosition());
                data.getSkills().forEach(System.out::println);
            });*/

            collectedData.forEach(dataPersistence::persist);
        };
    }
}
