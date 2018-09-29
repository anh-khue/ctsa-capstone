package io.anhkhue.ctsa.vietnamworksscraper.scraper.collector;

import io.anhkhue.ctsa.vietnamworksscraper.model.Position;
import io.anhkhue.ctsa.vietnamworksscraper.model.Skill;
import io.anhkhue.ctsa.vietnamworksscraper.scraper.converter.CtsaConverter;
import io.anhkhue.ctsa.vietnamworksscraper.scraper.datacleaner.DataCleaner;
import io.anhkhue.ctsa.vietnamworksscraper.communicator.elasticsearch.ElasticsearchExtractor;
import io.anhkhue.ctsa.vietnamworksscraper.exception.NoKeywordFoundException;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class VietnamworksCollector implements Collector {

    @Value("${ctsa.scraper.vietnamworks.main-url}")
    private String mainUrl;
    @Value("${ctsa.scraper.vietnamworks.business-field-url.it-software}")
    private String itUrl;
    @Value("${ctsa.selenium.system-properties.webdriver}")
    private String seleniumWebdriver;
    @Value("${ctsa.selenium.system-properties.geckodriver}")
    private String seleniumGeckodriver;
    @Value("${ctsa.scraper.vietnamworks.default-duration}")
    private int defaultDuration;

    private WebDriver driver;

    private final ElasticsearchExtractor elasticsearchExtractor;

    private final DataCleaner dataCleaner;

    public VietnamworksCollector(ElasticsearchExtractor elasticsearchExtractor,
                                 DataCleaner dataCleaner) {
        this.elasticsearchExtractor = elasticsearchExtractor;
        this.dataCleaner = dataCleaner;
    }

    private void openDriver(String url) {
        System.setProperty(seleniumWebdriver, seleniumGeckodriver);
        this.driver = new FirefoxDriver();
        this.driver.get(url);
    }

    private void closeDriver() {
        if (this.driver != null) {
            this.driver.close();
        }
    }

    @Override
    public List<CollectedDataModel> collectData() {
        List<String> detailUrls = getDetailUrls();

        List<CollectedDataModel> collectedData = new ArrayList<>();

        /*detailUrls.forEach(url -> {
            try {
                collectedData.add(retrieveDetail(url));
            } catch (NoKeywordFoundException e) {
                log.info("Skipped 1 link");
            }
        });*/

        for (int i = 0; i < 5; i++) {
            try {
                collectedData.add(retrieveDetail(detailUrls.get(i)));
            } catch (NoKeywordFoundException e) {
                log.info("Skipped 1 link");
            }
        }

        return collectedData;
    }

    private List<String> getDetailUrls() {
        openDriver(mainUrl + itUrl);
        try {
            List<WebElement> linkElements = this.driver.findElements(By.xpath("//a[@class=\"job-title\"]"));

            return linkElements.stream()
                               .map(element -> element.getAttribute("href"))
                               .collect(Collectors.toList());
        } finally {
            closeDriver();
        }
    }

    private CollectedDataModel retrieveDetail(String detailUrl) throws NoKeywordFoundException {
        openDriver(detailUrl);
        try {
            Position position = getPosition(this.driver);
            System.out.println(position.getName());

            WebElement tabPanels = this.driver.findElement(By.xpath("//div[@role=\"tabpanel\"]"));

            LocalDate postedDate = getPostedDate(tabPanels);

            String requiredSkills = getRequiredSkills(tabPanels);

            Set<Skill> skillsSet = new HashSet<>(dataCleaner.extractSkills(
                    elasticsearchExtractor.extractKeywords(requiredSkills)));
            skillsSet.forEach(skill -> System.out.println(skill.getName()));

            return CollectedDataModel.builder()
                                     .postedDate(postedDate)
                                     .position(position)
                                     .skills(skillsSet)
                                     .duration(defaultDuration)
                                     .build();
        } catch (IndexOutOfBoundsException e) {
            throw new NoKeywordFoundException();
        } finally {
            closeDriver();
        }
    }

    private boolean isLinkAvailable(String url) {
        openDriver(url);
        try {
            WebElement notFound = this.driver.findElement(By.id("no-results-message"));
            if (notFound != null) {
                System.out.println(notFound.getText());
                return false;
            }
        } finally {
            closeDriver();
        }
        return true;
    }

    private Position getPosition(WebDriver driver) throws NoKeywordFoundException {
        try {
            String title = driver.findElement(By.xpath("//h1[@class=\"job-title\"]"))
                                 .getText()
                                 .toLowerCase();

            return dataCleaner.extractPositions(elasticsearchExtractor.extractKeywords(title))
                              .get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new NoKeywordFoundException();
        }
    }

    private LocalDate getPostedDate(WebElement tabPanels) {
        return CtsaConverter.convertToLocalDate(tabPanels.findElement(
                By.xpath("//div[contains(@class,'box-summary')]/div[1]//span[@class=\"content\"]"))
                                                         .getText());
    }

    private String getRequiredSkills(WebElement tabPanels) {
        StringBuilder skillsBuilder = new StringBuilder();

        String skills = tabPanels.findElement(By.xpath("//div[contains(@class,'box-summary')]/div[4]//span[@class=\"content\"]"))
                                 .getText();
        String requirements = this.driver.findElement(By.xpath("//div[@class=\"requirements\"]"))
                                         .getText();

        skillsBuilder.append(skills)
                     .append(" ")
                     .append(requirements);

        return skillsBuilder.toString();
    }

}
