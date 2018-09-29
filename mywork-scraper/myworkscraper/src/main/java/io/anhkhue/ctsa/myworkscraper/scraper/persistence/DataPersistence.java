package io.anhkhue.ctsa.myworkscraper.scraper.persistence;

import io.anhkhue.ctsa.myworkscraper.model.PositionRequiresSkill;
import io.anhkhue.ctsa.myworkscraper.model.RecruitmentFact;
import io.anhkhue.ctsa.myworkscraper.scraper.collector.CollectedDataModel;
import io.anhkhue.ctsa.myworkscraper.service.PositionRequiresSkillService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataPersistence {

    private final PositionRequiresSkillService positionRequiresSkillService;

    public DataPersistence(PositionRequiresSkillService positionRequiresSkillService) {
        this.positionRequiresSkillService = positionRequiresSkillService;
    }

    public void persist(CollectedDataModel dataModel) {
        List<PositionRequiresSkill> prsList = retrievePositionRequiresSkill(dataModel);
        List<PositionRequiresSkill> persistedPrsList = persistNonExistedRecords(prsList);

        List<RecruitmentFactWrapper> factWrappers =
                persistedPrsList.stream()
                        .map(positionRequiresSkill ->
                                RecruitmentFactWrapper.builder()
                                        .positionRequiresSkill(positionRequiresSkill)
                                        .startDate(dataModel.getPostedDate())
//                                        .duration(dataModel.getDuration())
                                        .endDate(dataModel.getApprovalDate())
                                        .build())
                        .collect(Collectors.toList());

        List<RecruitmentFact> facts = factWrappers.stream()
                .map(this::createFactFromWrapper)
                .collect(Collectors.toList());
    }

    private List<PositionRequiresSkill> retrievePositionRequiresSkill(CollectedDataModel collectedDataModel) {
        int positionId = collectedDataModel.getPosition().getId();
        return collectedDataModel.getSkills().stream()
                .map(skill -> PositionRequiresSkill.builder()
                        .positionId(positionId)
                        .skillId(skill.getId())
                        .build())
                .collect(Collectors.toList());
    }

    private List<PositionRequiresSkill> persistNonExistedRecords(List<PositionRequiresSkill> positionRequiresSkills) {
        return positionRequiresSkills.stream()
                .map(positionRequiresSkill
                        -> positionRequiresSkillService.findByPositionIdAndSkillId(positionRequiresSkill.getPositionId(),
                        positionRequiresSkill.getSkillId())
                        .orElseGet(() -> positionRequiresSkillService.create(positionRequiresSkill)))
                .collect(Collectors.toList());
    }

    private RecruitmentFact createFactFromWrapper(RecruitmentFactWrapper wrapper) {
        Date startDate = Date.valueOf(wrapper.getStartDate());
//        Date endDate = Date.valueOf(wrapper.getStartDate().plusDays(wrapper.getDuration()));
        Date endDate = Date.valueOf(wrapper.getEndDate());

        return RecruitmentFact.builder()
                .startDate(startDate)
                .endDate(endDate)
                .positionRequiresSkillId(wrapper.getPositionRequiresSkill().getId())
                .count(3)
                .build();
    }
}


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class RecruitmentFactWrapper {

    private PositionRequiresSkill positionRequiresSkill;
    private LocalDate startDate;
    //    private int duration;
    private LocalDate endDate;
}
