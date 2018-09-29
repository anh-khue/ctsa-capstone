package io.anhkhue.ctsa.myworkscraper.scraper.collector;

import io.anhkhue.ctsa.myworkscraper.communicator.elasticsearch.ElasticsearchExtractor;
import io.anhkhue.ctsa.myworkscraper.exception.NoKeywordFoundException;
import io.anhkhue.ctsa.myworkscraper.model.Position;
import io.anhkhue.ctsa.myworkscraper.model.Skill;
import io.anhkhue.ctsa.myworkscraper.scraper.converter.CtsaConverter;
import io.anhkhue.ctsa.myworkscraper.scraper.datacleaner.DataCleaner;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Component
public class MyWorkCollector implements Collector {

    private static final String MAIN_URL = "https://mywork.com.vn/tuyen-dung/38/it-phan-mem.html?categories=38";

    private static final String SELENIUM_WEBDRIVER = "webdriver.gecko.driver";
    private static final String SELENIUM_GECKODRIVER = "geckodriver.exe";

    private static final String XPATH_ELEMENT_LINK = "//a[@class=\"el-tooltip item\"]";
    private static final String XPATH_POSITION = "//*[@id=\"detail-el\"]/div/div[2]/h1/span";
    private static final String XPATH_JOB_REQUIREMENTS = "//*[@id=\"__layout\"]/section/div[2]/div/section/div/div[1]/div[4]/div[1]/div[5]";
    private static final String XPATH_DATE_OF_APPROVAL = "//*[@id=\"detail-el\"]/div/div[3]/div/p[2]";
    private static final String XPATH_DATE_OF_SUBMISSION = "//*[@id=\"detail-el\"]/div/div[2]/p[3]/span";

    private WebDriver driver;

    private final ElasticsearchExtractor elasticsearchExtractor;

    private final DataCleaner dataCleaner;

    public MyWorkCollector(ElasticsearchExtractor elasticsearchExtractor,
                           DataCleaner dataCleaner) {
        this.elasticsearchExtractor = elasticsearchExtractor;
        this.dataCleaner = dataCleaner;
    }

    private void openDriver(String url) {
        System.setProperty(SELENIUM_WEBDRIVER, SELENIUM_GECKODRIVER);

        //Remove Gecko's logging
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

        driver = new FirefoxDriver();
        driver.get(url);
    }

    private void closeDriver() {
        if (driver != null) {
            driver.close();
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
        openDriver(MAIN_URL);
        try {
            List<WebElement> linkElements = this.driver.findElements(By.xpath(XPATH_ELEMENT_LINK));

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
            System.out.println("Chức vụ: " + position.getName());

            String posted = driver.findElement(By.xpath(XPATH_DATE_OF_SUBMISSION)).getText();
            String approval = getApprovalDate(driver.findElement(By.xpath(XPATH_DATE_OF_APPROVAL)).getText());

            System.out.println("Hạn nộp hồ sơ: " + posted);
            System.out.println("Ngày duyệt: " + approval);

            LocalDate postedDate = CtsaConverter.convertToLocalDate(posted);
            LocalDate approvalDate = CtsaConverter.convertToLocalDate(approval);

//            Duration duration = Duration.between(postedDate.atStartOfDay(), approvalDate.atStartOfDay());
//            long daysLeft = duration.toDays();
//            System.out.println("(Còn lại " + daysLeft + " ngày).");

            String requirements = driver.findElement(By.xpath(XPATH_JOB_REQUIREMENTS)).getText();

            Set<Skill> skillsSet = new HashSet<>(dataCleaner.extractSkills(
                    elasticsearchExtractor.extractKeywords(requirements)));
            skillsSet.forEach(skill -> System.out.println(skill.getName()));

            System.out.println("Yêu cầu công việc:\n" + skillsSet);

            return CollectedDataModel.builder()
                    .postedDate(postedDate)
                    .position(position)
                    .skills(skillsSet)
                    .approvalDate(approvalDate)
                    .build();
        } catch (IndexOutOfBoundsException e) {
            throw new NoKeywordFoundException();
        } finally {
            closeDriver();
        }
    }

    //Remove substring "Ngày duyệt: " from approval date string
    private String getApprovalDate(String data) {
        Pattern pattern = Pattern.compile(":(\\s)?");
        Matcher matcher = pattern.matcher(data);

        return matcher.find() ? data.substring(matcher.end()) : data;
    }

    private Position getPosition(WebDriver driver) throws NoKeywordFoundException {
        try {
            String title = driver.findElement(By.xpath(XPATH_POSITION))
                    .getText()
                    .toLowerCase();

            return dataCleaner.extractPositions(elasticsearchExtractor.extractKeywords(title))
                    .get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new NoKeywordFoundException();
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
}
