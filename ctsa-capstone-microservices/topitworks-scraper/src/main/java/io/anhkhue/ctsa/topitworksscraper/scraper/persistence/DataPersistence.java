package io.anhkhue.ctsa.topitworksscraper.scraper.persistence;

import io.anhkhue.ctsa.topitworksscraper.communicator.warehouseservice.WarehouseWebClient;
import io.anhkhue.ctsa.topitworksscraper.model.Recruitment;
import io.anhkhue.ctsa.topitworksscraper.model.RequiredSkill;
import io.anhkhue.ctsa.topitworksscraper.scraper.collector.CollectedDataModel;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataPersistence {

//    @Value("${ctsa.scraper.mywork.default-number}")
//    private int number;

    private final WarehouseWebClient warehouseWebClient;

    public DataPersistence(WarehouseWebClient warehouseWebClient) {
        this.warehouseWebClient = warehouseWebClient;
    }

    public void persist(CollectedDataModel dataModel) {
        Recruitment recruitment = Recruitment.builder()
                                             .link(dataModel.getLink())
                                             .source("Mywork")
                                             .startDate(Date.valueOf(dataModel.getPostedDate()))
//                                             .endDate(Date.valueOf(dataModel.getPostedDate().plusDays(dataModel.getDuration())))
                                             .endDate(Date.valueOf(dataModel.getApprovalDate()))
                                             .positionId(dataModel.getPosition().getId())
                                             .number(dataModel.getNumber())
                                             .build();

        int recruitmentId = warehouseWebClient.saveRecruitment(recruitment).getId();
        recruitment.setId(recruitmentId);

        List<RequiredSkill> requiredSkills = dataModel.getSkills().stream()
                                                      .map(skill -> RequiredSkill.builder()
                                                                                 .recruitmentId(recruitmentId)
                                                                                 .skillId(skill.getId())
                                                                                 .build())
                                                      .collect(Collectors.toList());
        requiredSkills.forEach(warehouseWebClient::saveRequiredSkill);
    }
}