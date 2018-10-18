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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class ResultsSuggestionsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResultsSuggestionsServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
        };
    }
}