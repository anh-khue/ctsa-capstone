package io.anhkhue.ctsa.vietnamworksscraper.scraper.collector;

import io.anhkhue.ctsa.vietnamworksscraper.communicator.elasticsearch.ElasticsearchWebClient;
import io.anhkhue.ctsa.vietnamworksscraper.exception.NoKeywordFoundException;
import io.anhkhue.ctsa.vietnamworksscraper.model.Link;
import io.anhkhue.ctsa.vietnamworksscraper.model.Position;
import io.anhkhue.ctsa.vietnamworksscraper.model.Skill;
import io.anhkhue.ctsa.vietnamworksscraper.repository.LinkRepository;
import io.anhkhue.ctsa.vietnamworksscraper.scraper.converter.CtsaConverter;
import io.anhkhue.ctsa.vietnamworksscraper.scraper.datacleaner.DataCleaner;
import io.anhkhue.ctsa.vietnamworksscraper.scraper.persistence.DataPersistence;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
    @Value("${ctsa.selenium.system-properties.chromedriver}")
    private String seleniumChromedriver;
    @Value("${ctsa.scraper.vietnamworks.default-duration}")
    private int defaultDuration;

    private WebDriver driver;

    private final ElasticsearchWebClient elasticsearchWebClient;

    private final DataCleaner dataCleaner;

    private final LinkRepository linkRepository;

    private final DataPersistence dataPersistence;

    public VietnamworksCollector(ElasticsearchWebClient elasticsearchWebClient,
                                 DataCleaner dataCleaner,
                                 LinkRepository linkRepository,
                                 DataPersistence dataPersistence) {
        this.elasticsearchWebClient = elasticsearchWebClient;
        this.dataCleaner = dataCleaner;
        this.linkRepository = linkRepository;
        this.dataPersistence = dataPersistence;
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

        for (Link link : detailUrls) {
            try {
                CollectedDataModel dataModel = retrieveDetail(link.getUrl());
                if (dataModel != null && !dataModel.getSkills().isEmpty()) {
                    dataPersistence.persist(dataModel);
                }
                collectedData.add(dataModel);
                link.setCrawled(true);
                linkRepository.save(link);
            } catch (NoKeywordFoundException e) {
                log.info("Skipped 1 link");
            }
        }

        // For testing
        /*for (int i = 0; i < 3; i++) {
            try {
                collectedData.add(retrieveDetail(detailUrls.get(i).getUrl()));
            } catch (NoKeywordFoundException e) {
                log.info("Skipped 1 link");
            }
        }*/

        return collectedData;
    }

    private void getDetailUrls() {
        openDriver(mainUrl + itUrl);
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, 20);

            /*WebElement activePage = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//li[contains(@class, \"ais-pagination--item__active active\")]")));*/

            WebElement activePage = this.driver.findElement(
                    By.xpath("//li[contains(@class, \"ais-pagination--item__active active\")]"));
            while (activePage != null) {
                try {
                    activePage = this.driver.findElement(
                            By.xpath("//li[contains(@class, \"ais-pagination--item__active active\")]"));
                    log.info("Retrieving data from page " + activePage.getText() + " in vietnamworks.com");

                   /* List<WebElement> linkElements = wait.until(
                            ExpectedConditions.presenceOfAllElementsLocatedBy(
                                    By.xpath("//a[contains(@class, \"job-title\")]")));*/

                    List<WebElement> linkElements = this.driver.findElements(
                            By.xpath("//a[contains(@class, \"job-title\")]"));

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
                                                    .findElement(By.xpath("./a[contains(@class,\"ais-pagination--link\")]"));
                    nextPage.click();
                    log.info("Continue to page " + nextPage.getText());
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
        log.info("Retrieving information from: " + detailUrl);
        try {
            Position position = getPosition();
            log.info("Position: " + position.getName());

            int duration = Integer.parseInt(this.driver.findElement(By.xpath("//span[contains(@class, \"expiry\")]"))
                                                       .getText()
                                                       .replaceAll("\\D+", ""));
            log.info("Duration: " + duration);

            WebElement tabPanels = this.driver.findElement(By.xpath("//div[@role=\"tabpanel\"]"));

            LocalDate postedDate = getPostedDate(tabPanels);
            log.info("Posted date: " + postedDate);

            String requiredSkills = getRequiredSkills(tabPanels);

            Set<Skill> skillsSet = new HashSet<>(dataCleaner.extractSkills(
                    elasticsearchWebClient.extractKeywords(requiredSkills)));
            log.info("Skills: ");
            skillsSet.forEach(skill -> log.info(skill.getName()));

            return CollectedDataModel.builder()
                                     .link(detailUrl)
                                     .postedDate(postedDate)
                                     .position(position)
                                     .skills(skillsSet)
                                     .duration(duration != 0 ? duration : defaultDuration)
                                     .build();
        } catch (IndexOutOfBoundsException e) {
            throw new NoKeywordFoundException();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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

    private Position getPosition() throws NoKeywordFoundException {
        try {
            String title = this.driver.findElement(By.xpath("//h1[contains(@class,\"job-title\")]"))
                                      .getText()
                                      .toLowerCase();

            return dataCleaner.extractPositions(elasticsearchWebClient.extractKeywords(title))
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
