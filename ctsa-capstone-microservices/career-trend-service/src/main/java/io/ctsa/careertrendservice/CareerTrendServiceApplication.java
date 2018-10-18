package io.ctsa.careertrendservice;

import io.ctsa.careertrendservice.model.HumanResource;
import io.ctsa.careertrendservice.model.Salary;
import io.ctsa.careertrendservice.model.SupportingInformation;
import io.ctsa.careertrendservice.prediction.ExponentialSmoothingFormula;
import io.ctsa.careertrendservice.repository.HumanResourcesRepository;
import io.ctsa.careertrendservice.repository.SalaryRepository;
import io.ctsa.careertrendservice.repository.SupportingInformationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CareerTrendServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CareerTrendServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ExponentialSmoothingFormula formula,
                             HumanResourcesRepository humanResourcesRepository,
                             SalaryRepository salaryRepository,
                             SupportingInformationRepository supportingInformationRepository) {
        return args -> Arrays.asList(1, 4, 5)
                             .forEach(majorId -> {
                                 List<HumanResource> humanResources = humanResourcesRepository
                                         .findByMajorIdOrderByYearAsc(majorId);

                                 humanResources = formula.exponentialSmooth(humanResources);
                                 humanResourcesRepository.saveAll(humanResources);

                                 List<Salary> salaries = salaryRepository.findAllByMajorIdOrderByYearAsc(majorId);
                                 salaries = formula.exponentialSmooth(salaries);
                                 salaryRepository.saveAll(salaries);

                                 List<SupportingInformation> supportingInformationList =
                                         supportingInformationRepository.findAllByMajorIdOrderByYearAsc(majorId);
                                 supportingInformationList = formula.exponentialSmooth(supportingInformationList);
                                 supportingInformationRepository.saveAll(supportingInformationList);
                             });
    }
}
