package io.ctsa.careertrendservice;

import io.ctsa.careertrendservice.model.HumanResources;
import io.ctsa.careertrendservice.prediction.ExponentialSmoothingFormula;
import io.ctsa.careertrendservice.prediction.PredictionModel;
import io.ctsa.careertrendservice.repository.HumanResourcesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CareerTrendServiceApplication {

    public CareerTrendServiceApplication(HumanResourcesRepository humanResourcesRepository) {
        this.humanResourcesRepository = humanResourcesRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(CareerTrendServiceApplication.class, args);
	}

	private final HumanResourcesRepository humanResourcesRepository;

	@Bean
	CommandLineRunner runner(ExponentialSmoothingFormula formula) {
	    return args -> {
            List<HumanResources> list = humanResourcesRepository.findAllByCareerIdOrderByYearAsc(1);
            List<PredictionModel> predictionModels = new ArrayList<>();
            for (HumanResources hr : list) {
                PredictionModel model = new HumanResources();
                model.setYear(hr.getYear());
                model.setActual(hr.getActual());
                predictionModels.add(model);
            }
            formula.exponentialSmoothie(predictionModels);
        };
    }
}
