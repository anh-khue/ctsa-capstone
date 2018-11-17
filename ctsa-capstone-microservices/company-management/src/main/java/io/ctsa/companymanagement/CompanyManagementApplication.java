package io.ctsa.companymanagement;

import io.ctsa.companymanagement.model.Recruitment;
import io.ctsa.companymanagement.service.RecruitmentService;
import io.ctsa.companymanagement.stream.producer.ProducerChannels;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.util.Date;

@SpringBootApplication
@EnableEurekaClient
@EnableBinding(ProducerChannels.class)
public class CompanyManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompanyManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(RecruitmentService recruitmentService) {
        return args -> {
            Recruitment recruitment = Recruitment.builder()
                                                 .title("Test new recruitment")
                                                 .positionId(1)
                                                 .startDate(new Timestamp(System.currentTimeMillis()))
                                                 .endDate(new Timestamp(System.currentTimeMillis()))
                                                 .number(3)
                                                 .jobDescription("Test description")
                                                 .jobRequirement("Test requirement")
                                                 .published(1)
                                                 .email("test@gmail.com")
                                                 .phone("1234567890")
                                                 .address("Test Address")
                                                 .companyId(4)
                                                 .build();

            recruitmentService.create(recruitment);
//            recruitmentService.getById(1).ifPresent(recruitment -> {
//                System.out.println(new Date(recruitment.getStartDate().geta));
//            });
        };
    }
}
