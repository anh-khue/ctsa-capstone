package io.ctsa.resultssuggestionsservice;

import io.ctsa.resultssuggestionsservice.model.*;
import io.ctsa.resultssuggestionsservice.repository.EntranceExamResultRepository;
import io.ctsa.resultssuggestionsservice.suggestion.cluster.MajorCluster;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ResultsSuggestionsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResultsSuggestionsServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(MajorCluster majorCluster,
                             EntranceExamResultRepository entranceExamResultRepository) {
        return args -> {
            MajorCentroid majorCentroid = majorCluster.findCentroid(1);
            System.out.println("Entrance Exam Result");
            EntranceExamResultCentroid entranceExamResultCentroid = majorCentroid.getEntranceExamResultCentroid();
            System.out.println("Math: " + entranceExamResultCentroid.getMath());
            System.out.println("Literature: " + entranceExamResultCentroid.getLiterature());
            System.out.println("English: " + entranceExamResultCentroid.getEnglish());
            System.out.println("Physics: " + entranceExamResultCentroid.getPhysics());
            System.out.println("Chemistry: " + entranceExamResultCentroid.getChemistry());
            System.out.println("Biology: " + entranceExamResultCentroid.getBiology());
            System.out.println("History: " + entranceExamResultCentroid.getHistory());
            System.out.println("Geography: " + entranceExamResultCentroid.getGeography());

            System.out.println("High School Top Results");
            List<HighSchoolTopResultCentroid> highSchoolTopResults = majorCentroid.getHighSchoolTopResultCentroids();
            highSchoolTopResults.forEach(highSchoolTopResultCentroid -> {
                System.out.println(highSchoolTopResultCentroid.getSubjectId() + " : "
                                           + highSchoolTopResultCentroid.getMark() + " : "
                                           + highSchoolTopResultCentroid.getWeight());
            });
        };
    }
}
