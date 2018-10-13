package io.anhkhue.ctsa.myworkscraper.scraper.collector;

import io.anhkhue.ctsa.myworkscraper.communicator.elasticsearch.ElasticsearchWebClient;
import io.anhkhue.ctsa.myworkscraper.exception.NoKeywordFoundException;
import io.anhkhue.ctsa.myworkscraper.model.Link;
import io.anhkhue.ctsa.myworkscraper.model.Position;
import io.anhkhue.ctsa.myworkscraper.model.Skill;
import io.anhkhue.ctsa.myworkscraper.repository.LinkRepository;
import io.anhkhue.ctsa.myworkscraper.scraper.converter.CtsaConverter;
import io.anhkhue.ctsa.myworkscraper.scraper.datacleaner.DataCleaner;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class MyworkCollector implements Collector {

    @Value("${ctsa.scraper.mywork.main-url}")
    private String mainUrl;
    @Value("${ctsa.scraper.mywork.business-field-url.it-software}")
    private String itUrl;
    @Value("${ctsa.selenium.system-properties.webdriver}")
    private String seleniumWebdriver;
    @Value("${ctsa.selenium.system-properties.chromedriver}")
    private String seleniumChromedriver;
//    @Value("${ctsa.scraper.mywork.default-duration}")
//    private int defaultDuration;

    //    private static final String XPATH_ELEMENT_LINK = "//a[@class=\"el-tooltip item\"]";
    private static final String XPATH_POSITION = "//*[@id=\"detail-el\"]/div/div[2]/h1/span";
    private static final String XPATH_JOB_REQUIREMENTS = "//*[@id=\"__layout\"]/section/div[2]/div/section/div/div[1]/div[4]/div[1]/div[5]";
    private static final String XPATH_DATE_OF_APPROVAL = "//*[@id=\"detail-el\"]/div/div[3]/div/p[2]";
    private static final String XPATH_DATE_OF_SUBMISSION = "//*[@id=\"detail-el\"]/div/div[2]/p[3]/span";
    private static final String XPATH_NUMBER_OF_RECRUITMENT = "//*[@id=\"__layout\"]/section/div[2]/div/section/div/div[1]/div[4]/div[1]/div[1]/div[1]/p[3]";

    private WebDriver driver;

    private final ElasticsearchWebClient elasticsearchWebClient;

    private final DataCleaner dataCleaner;

    private final LinkRepository linkRepository;

    public MyworkCollector(ElasticsearchWebClient elasticsearchWebClient,
                           DataCleaner dataCleaner,
                           LinkRepository linkRepository) {
        this.elasticsearchWebClient = elasticsearchWebClient;
        this.dataCleaner = dataCleaner;
        this.linkRepository = linkRepository;
    }

    private void openDriver(String url) {
        System.setProperty(seleniumWebdriver, seleniumChromedriver);
        this.driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(url);
    }

    private void closeDriver() {
        if (this.driver != null) {
            this.driver.close();
        }
    }

    @Override
    public List<CollectedDataModel> collectData() {
//        getDetailUrls();
        List<Link> detailUrls = linkRepository.findAll();
        log.info("Total urls: " + detailUrls.size());
        List<CollectedDataModel> collectedData = new ArrayList<>();

        /*detailUrls.forEach(link -> {
            try {
                this.driver.get(link.getUrl());
                collectedData.add(retrieveDetail(link.getUrl()));
                link.setCrawled(true);
                linkRepository.save(link);
            } catch (NoKeywordFoundException e) {
                log.info("Skipped 1 link");
            } finally {
                closeDriver();
            }
        });*/

        /*for (Link link : detailUrls) {
            try {
                collectedData.add(retrieveDetail(link.getUrl()));
                link.setCrawled(true);
                linkRepository.save(link);
            } catch (NoKeywordFoundException e) {
                log.info("Skipped 1 link.");
            }
        }*/

        // For testing
        for (int i = 0; i < 5; i++) {
            try {
                collectedData.add(retrieveDetail(detailUrls.get(i).getUrl()));
            } catch (NoKeywordFoundException e) {
                log.info("Skipped 1 link");
            }
        }

        return collectedData;
    }

    private void getDetailUrls() {
        openDriver(mainUrl + itUrl);
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, 20);

            /*WebElement activePage = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//li[contains(@class, \"ais-pagination--item__active active\")]")));*/

            WebElement activePage = this.driver.findElement(
                    By.xpath("//li[contains(@class, \"page-item active\")]"));
            while (activePage != null) {
                try {
                    activePage = this.driver.findElement(
                            By.xpath("//li[contains(@class, \"page-item active\")]"));
                    log.info("Retrieving data from: " + activePage.getText());

                   /* List<WebElement> linkElements = wait.until(
                            ExpectedConditions.presenceOfAllElementsLocatedBy(
                                    By.xpath("//a[contains(@class, \"job-title\")]")));*/

                    List<WebElement> linkElements = this.driver.findElements(
                            By.xpath("//a[contains(@class, \"el-tooltip item\")]"));

                    linkElements.forEach(element -> {
                        String url = element.getAttribute("href");

                        if (!linkRepository.findByUrl(url).isPresent()) {
                            linkRepository.save(Link.builder()
                                    .url(url)
                                    .isCrawled(false)
                                    .build());
                        }
                    });

                    WebElement nextPage = activePage.findElement(By.xpath("following-sibling::*"))
                            .findElement(By.xpath("./a[contains(@class,\"page-link\")]"));

                    WebElement nextPageClickable = wait.until(ExpectedConditions.elementToBeClickable(nextPage));
                    nextPageClickable.click();
                    log.info("Continue to page: " + nextPage.getText());
                } catch (StaleElementReferenceException e) {
                    log.info("Skipped 1 page.");
                } catch (NoSuchElementException e) {
                    log.info("No more page. Stop crawling.");
                    activePage = null;
                }
            }
        } catch (Exception e) {
            log.info(e.getLocalizedMessage());
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

            int number = getNumberOfRecruitment(driver.findElement(By.xpath(XPATH_NUMBER_OF_RECRUITMENT)).getText());

            String requirements = driver.findElement(By.xpath(XPATH_JOB_REQUIREMENTS)).getText();

            Set<Skill> skillsSet = new HashSet<>(dataCleaner.extractSkills(
                    elasticsearchWebClient.extractKeywords(requirements)));
            log.info("Yêu cầu công việc: ");
            skillsSet.forEach(skill -> log.info(skill.getName()));

            return CollectedDataModel.builder()
                    .link(detailUrl)
                    .postedDate(postedDate)
                    .position(position)
                    .skills(skillsSet)
                    .approvalDate(approvalDate)
                    .number(number)
                    .build();
        } catch (IndexOutOfBoundsException e) {
            throw new NoKeywordFoundException();
        } catch (Exception e) {
//            e.printStackTrace();
            return null;
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

            return dataCleaner.extractPositions(elasticsearchWebClient.extractKeywords(title))
                    .get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new NoKeywordFoundException();
        }
    }

    private int getNumberOfRecruitment(String data) {
        try {
            //Remove substring "Số lượng cần tuyển: " from number of recruitment string
            int number = Integer.parseInt(getApprovalDate(data));
            return (number == 0) ? 1 : number;
        } catch (NumberFormatException e) {
            return 1;
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
