package io.anhkhue.ctsa.topitworksscraper.scraper.collector;

import io.anhkhue.ctsa.topitworksscraper.communicator.elasticsearch.ElasticsearchWebClient;
import io.anhkhue.ctsa.topitworksscraper.exception.NoKeywordFoundException;
import io.anhkhue.ctsa.topitworksscraper.model.Link;
import io.anhkhue.ctsa.topitworksscraper.model.Position;
import io.anhkhue.ctsa.topitworksscraper.model.Skill;
import io.anhkhue.ctsa.topitworksscraper.repository.LinkRepository;
import io.anhkhue.ctsa.topitworksscraper.scraper.datacleaner.DataCleaner;
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

@Slf4j
@Component
public class TopitworksCollector implements Collector {

    @Value("${ctsa.scraper.topitworks.main-url}")
    private String mainUrl;
    @Value("${ctsa.scraper.topitworks.business-field-url.it-software}")
    private String itUrl;
    @Value("${ctsa.selenium.system-properties.webdriver}")
    private String seleniumWebdriver;
    @Value("${ctsa.selenium.system-properties.chromedriver}")
    private String seleniumChromedriver;
//    @Value("${ctsa.scraper.mywork.default-duration}")
//    private int defaultDuration;

    private static final String XPATH_ELEMENT_LINK = "//*[@id=\"hits\"]/div/div[2]/div/div/div/div[1]/h4/a";
    private static final String XPATH_POSITION = "//div[contains(@class,\"job-header-wrapper\")]/h3";
    private static final String XPATH_JOB_REQUIREMENTS = "//div[@class=\"read-more-panel-ex mt2\"]";
    private static final String XPATH_RELATED_SKILLS = "//div[@class=\"skill-tags mt1\"]";
    private static final String XPATH_POSTED_DATE = "//div[contains(@class,\"job-header-wrapper\")]/span[@class=\"job-post-day\"]";

    private WebDriver driver;

    private final ElasticsearchWebClient elasticsearchWebClient;

    private final DataCleaner dataCleaner;

    private final LinkRepository linkRepository;

    public TopitworksCollector(ElasticsearchWebClient elasticsearchWebClient,
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

    private void getDetailUrls() {
        openDriver(mainUrl + itUrl);
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, 20);

            /*WebElement activePage = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//li[contains(@class, \"ais-pagination--item__active active\")]")));*/

            WebElement activePage = this.driver.findElement(
                    By.xpath("//*[@id=\"pagination\"]/ul/li[@class=\"active \"]"));
            while (activePage != null) {
                try {
                    activePage = this.driver.findElement(
                            By.xpath("//*[@id=\"pagination\"]/ul/li[@class=\"active \"]"));
                    log.info("Retrieving data from: " + activePage.getText());

                   /* List<WebElement> linkElements = wait.until(
                            ExpectedConditions.presenceOfAllElementsLocatedBy(
                                    By.xpath("//a[contains(@class, \"job-title\")]")));*/

                    List<WebElement> linkElements = this.driver.findElements(
                            By.xpath(XPATH_ELEMENT_LINK));

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
                            .findElement(By.xpath("./a"));

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

    private LocalDate getPostedDate(String data) {
        try {
            data = data.substring(data.indexOf("Đ"), data.lastIndexOf("-"));
            data = data.replaceAll("\\D+", "");
            int count = Integer.parseInt(data);
            return LocalDate.now().minusDays(count);
        } catch (Exception e) {
            return LocalDate.now();
        }
    }

    private CollectedDataModel retrieveDetail(String detailUrl) throws NoKeywordFoundException {
        openDriver(detailUrl);
        try {
            Position position = getPosition(this.driver);
            System.out.println("Chức vụ: " + position.getName());

            //Posted date example: "Đã đăng 15 ngày trước - 144 lượt xem"
            String posted = driver.findElement(By.xpath(XPATH_POSTED_DATE)).getText().trim();
            LocalDate postedDate = getPostedDate(posted);
            System.out.println("Ngày đăng tin: " + postedDate);

            LocalDate approvalDate = postedDate.plusDays(30);
            System.out.println("Ngày hết hạn: " + approvalDate);

            String requirements = driver.findElement(By.xpath(XPATH_JOB_REQUIREMENTS)).getText();
//            System.out.println("Yêu cầu công việc:\n" + requirements);

            Set<Skill> skillsSet = new HashSet<>(dataCleaner.extractSkills(
                    elasticsearchWebClient.extractKeywords(requirements)));

            WebElement related = driver.findElement(By.xpath(XPATH_RELATED_SKILLS));
            List<WebElement> relatedSkills = related.findElements(By.xpath(XPATH_RELATED_SKILLS + "/a"));
            System.out.println("Kỹ năng:");
            relatedSkills.forEach(element -> {
                System.out.println(element.getText());
                skillsSet.addAll(
                            dataCleaner.extractSkills(
                                    elasticsearchWebClient.extractKeywords(
                                            element.getText())));
            });

            log.info("Yêu cầu công việc: ");
            skillsSet.forEach(skill -> log.info(skill.getName()));

            return CollectedDataModel.builder()
                    .link(detailUrl)
                    .postedDate(postedDate)
                    .position(position)
                    .skills(skillsSet)
                    .approvalDate(approvalDate)
                    .number(1)
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
        for (int i = 0; i < 10; i++) {
            try {
                collectedData.add(retrieveDetail(detailUrls.get(i).getUrl()));
            } catch (NoKeywordFoundException e) {
                log.info("Skipped 1 link");
            }
        }

        return collectedData;
    }
}
