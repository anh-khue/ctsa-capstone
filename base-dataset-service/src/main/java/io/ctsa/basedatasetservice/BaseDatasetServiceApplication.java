package io.ctsa.basedatasetservice;

import io.ctsa.basedatasetservice.client.ElasticsearchWebClient;
import io.ctsa.basedatasetservice.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BaseDatasetServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseDatasetServiceApplication.class, args);
    }

    @Autowired
    @Bean
    CommandLineRunner runner(KeywordService keywordService,
                             ElasticsearchWebClient elasticsearchWebClient) {
        return args -> {
            String content = "1.\tUniversity graduate in IT or equivalent\n" +
                    "2.\tExperience in web applications development (prefer python, PHP, java, but not must)\n" +
                    "3.\tStrong in OOP programing language such as Python, Java, PHP, C#…\n" +
                    "4.\tStrong with JavaScript frameworks such as Ext JS, Angular JS, React JS, Bootstrap…\n" +
                    "5.\tKnowledge of HTML, CSS, Ajax, JavaScript libraries, jQuery...\n" +
                    "6.\tHave good knowledge of SQL, familiar with databases such as Oracle, MySQL…\n" +
                    "7.\tGood ability to self-training on latest technologies such as Google Cloud, MS Cloud, IoT, Robot, AI…\n" +
                    "8.\tActive, work well under pressure and problem-solving abilities\n" +
                    "9.\tFull-time permanent (Japanese style)";
            List<String> hits = elasticsearchWebClient.extractKeywords(content.replaceAll(",", " ")
                                                                              .replaceAll("…", " ")
                                                                              .replaceAll("\\.", " ")
                                                                              .replaceAll("\\(", " ")
                                                                              .replaceAll("\\)", " ")
                                                                              .toLowerCase());
            hits.forEach(System.out::println);
        };
    }
}
