package io.ctsa.resultssuggestionsservice;

import io.ctsa.resultssuggestionsservice.model.*;
import io.ctsa.resultssuggestionsservice.repository.EntranceExamResultRepository;
import io.ctsa.resultssuggestionsservice.repository.MajorRepository;
import io.ctsa.resultssuggestionsservice.suggestion.cluster.MajorCluster;
import io.ctsa.resultssuggestionsservice.suggestion.recommendation.MajorSuggestion;
import io.ctsa.resultssuggestionsservice.suggestion.recommendation.PupilSuggestion;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ResultsSuggestionsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResultsSuggestionsServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(PupilSuggestion pupilSuggestion,
                             MajorRepository majorRepository) {
        return args -> {
            SuggestedMajor input = new SuggestedMajor();
            input.setCharacteristic(1);
            input.setHighSchoolAverage(7.8);

            HighSchoolTopInput topInput1 = new HighSchoolTopInput();
            topInput1.setSubjectId(1);
            topInput1.setMark(8.5);
            topInput1.setWeight(1);

            HighSchoolTopInput topInput2 = new HighSchoolTopInput();
            topInput1.setSubjectId(4);
            topInput1.setMark(8);
            topInput1.setWeight(2);

            HighSchoolTopInput topInput3 = new HighSchoolTopInput();
            topInput1.setSubjectId(3);
            topInput1.setMark(7.5);
            topInput1.setWeight(3);

            List<HighSchoolTopInput> topInputs = Arrays.asList(topInput1, topInput2, topInput3);
            input.setHighSchoolTopInputs(topInputs);

            List<MajorSuggestion> suggestions = pupilSuggestion.suggest(input);
            suggestions.forEach(suggestion -> {
                System.out.println(suggestion.getMajor().getName());
                System.out.println(suggestion.getDistance());
            });
        };
    }
}
