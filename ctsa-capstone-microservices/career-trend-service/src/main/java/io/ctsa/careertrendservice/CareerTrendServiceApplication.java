package io.ctsa.careertrendservice;

import io.ctsa.careertrendservice.model.HumanResource;
import io.ctsa.careertrendservice.model.Salary;
import io.ctsa.careertrendservice.model.SupportingInformation;
import io.ctsa.careertrendservice.prediction.storage.SmoothingParams;
import io.ctsa.careertrendservice.prediction.storage.SmoothingParamsConstants;
import io.ctsa.careertrendservice.prediction.timeseries.ExponentialSmoothingFormula;
import io.ctsa.careertrendservice.repository.HumanResourcesRepository;
import io.ctsa.careertrendservice.repository.SalaryRepository;
import io.ctsa.careertrendservice.repository.SupportingInformationRepository;
import io.ctsa.careertrendservice.service.HumanResourcesService;
import io.ctsa.careertrendservice.service.SalaryService;
import io.ctsa.careertrendservice.service.SmoothingParamsService;
import io.ctsa.careertrendservice.service.SupportingInformationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@EnableEurekaClient
@SpringBootApplication
public class CareerTrendServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CareerTrendServiceApplication.class, args);
    }

    /*@Bean
    CommandLineRunner runner(ExponentialSmoothingFormula formula,
                             HumanResourcesRepository humanResourcesRepository,
                             SalaryRepository salaryRepository,
                             SupportingInformationRepository supportingInformationRepository,
                             HumanResourcesService humanResourcesService,
                             SalaryService salaryService,
                             SupportingInformationService supportingInformationService,
                             SmoothingParamsService smoothingParamsService) {
        return args -> Arrays.asList(1, 4, 5).forEach(majorId -> {
            humanResourcesService.estimateSmoothingParams(majorId);
            salaryService.estimateSmoothingParams(majorId);
            supportingInformationService.estimateSmoothingParams(majorId);

            List<HumanResource> humanResources = humanResourcesRepository
                    .findByMajorIdOrderByYearAsc(majorId);
            SmoothingParams humanResourcesSmoothingParams = smoothingParamsService
                    .getSmoothingParams(SmoothingParamsConstants.HUMAN_RESOURCES + "-" + majorId);
            humanResources = formula.exponentialSmooth(humanResources,
                                                       humanResourcesSmoothingParams.getAlpha(),
                                                       humanResourcesSmoothingParams.getBeta());
            humanResourcesRepository.saveAll(humanResources);

            List<Salary> salaries = salaryRepository.findByMajorIdOrderByYearAsc(majorId);
            SmoothingParams salarySmoothingParams = smoothingParamsService
                    .getSmoothingParams(SmoothingParamsConstants.SALARY + "-" + majorId);
            salaries = formula.exponentialSmooth(salaries,
                                                 salarySmoothingParams.getAlpha(),
                                                 salarySmoothingParams.getBeta());
            salaryRepository.saveAll(salaries);

            List<SupportingInformation> supportingInformationList =
                    supportingInformationRepository.findByMajorIdOrderByYearAsc(majorId);
            SmoothingParams supportingInformationSmoothingParams = smoothingParamsService
                    .getSmoothingParams(SmoothingParamsConstants.SUPPORTING_INFORMATION + "-" + majorId);
            supportingInformationList = formula.exponentialSmooth(supportingInformationList,
                                                                  supportingInformationSmoothingParams.getAlpha(),
                                                                  supportingInformationSmoothingParams.getBeta());
            supportingInformationRepository.saveAll(supportingInformationList);
        });
    }*/
}
