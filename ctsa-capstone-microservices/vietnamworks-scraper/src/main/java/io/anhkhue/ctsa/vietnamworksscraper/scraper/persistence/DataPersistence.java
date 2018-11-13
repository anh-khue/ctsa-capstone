package io.anhkhue.ctsa.vietnamworksscraper.scraper.persistence;

import io.anhkhue.ctsa.vietnamworksscraper.communicator.warehouseservice.WarehouseWebClient;
import io.anhkhue.ctsa.vietnamworksscraper.model.Recruitment;
import io.anhkhue.ctsa.vietnamworksscraper.model.RequiredSkill;
import io.anhkhue.ctsa.vietnamworksscraper.scraper.collector.CollectedDataModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataPersistence {

    @Value("${ctsa.scraper.vietnamworks.default-number}")
    private int number;

    private final WarehouseWebClient warehouseWebClient;

    public DataPersistence(WarehouseWebClient warehouseWebClient) {
        this.warehouseWebClient = warehouseWebClient;
    }

    public void persist(CollectedDataModel dataModel) {
        Recruitment recruitment = Recruitment.builder()
                                             .link(dataModel.getLink())
                                             .source("Vietnamworks")
                                             .startDate(Date.valueOf(dataModel.getPostedDate()))
                                             .endDate(Date.valueOf(dataModel.getPostedDate()
                                                                            .plusDays(dataModel.getDuration())))
                                             .positionId(dataModel.getPosition().getId())
                                             .number(number)
                                             .build();

        int recruitmentId = warehouseWebClient.saveRecruitment(recruitment).getId();
        recruitment.setId(recruitmentId);

        List<RequiredSkill> requiredSkills = dataModel.getSkills()
                                                      .stream()
                                                      .map(skill -> RequiredSkill.builder()
                                                                                 .recruitmentId(recruitmentId)
                                                                                 .skillId(skill.getId())
                                                                                 .build())
                                                      .collect(Collectors.toList());
        requiredSkills.forEach(warehouseWebClient::saveRequiredSkill);
    }
}