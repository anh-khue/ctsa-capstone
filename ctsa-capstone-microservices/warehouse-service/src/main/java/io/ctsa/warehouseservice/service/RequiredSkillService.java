package io.ctsa.warehouseservice.service;

import io.ctsa.warehouseservice.model.RequiredSkill;
import io.ctsa.warehouseservice.model.SkillPredictionModel;
import io.ctsa.warehouseservice.repository.RequiredSkillRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequiredSkillService {

    private final RequiredSkillRepository requiredSkillRepository;

    public RequiredSkillService(RequiredSkillRepository requiredSkillRepository) {
        this.requiredSkillRepository = requiredSkillRepository;
    }

    public RequiredSkill saveRequiredSkill(RequiredSkill requiredSkill) {
        requiredSkillRepository.save(requiredSkill);
        requiredSkillRepository.flush();
        return requiredSkill;
    }

    public Integer findTotalPagesTopSkillsByPositionAndSkillType(Integer positionId,
                                                                 Integer skillTypeId,
                                                                 Integer skillsPerPage) {
        List<Integer> allSkills = requiredSkillRepository.findAllTopSkillsByPositionAndSkillType(positionId,
                                                                                                 skillTypeId);

        return (int) Math.ceil((double) allSkills.size() / skillsPerPage);
    }

    public List<Integer> findTopSkillsByPositionAndSkillType(Integer positionId,
                                                             Integer skillTypeId,
                                                             Integer page,
                                                             Integer skillsPerPage) {
        return requiredSkillRepository.findTopSkillsByPositionAndSkillType(positionId,
                                                                           skillTypeId,
                                                                           skillsPerPage,
                                                                           (page - 1) * skillsPerPage);
    }

    public List<Integer> findTopSkillTypesByPositionEscape(Integer positionId, Integer skillTypeId) {
        if (skillTypeId == null) {
            return requiredSkillRepository.findTopSkillTypesByPosition(positionId);
        }
        return requiredSkillRepository.findTopSkillTypesByPositionEscapeSkillType(positionId, skillTypeId);
    }

    public List<Integer> findTopSkillTypesByPosition(Integer positionId, Integer exceptedSkillTypeId) {
        if (exceptedSkillTypeId == null) {
            return requiredSkillRepository.findTopSkillTypesByPosition(positionId);
        }
        return requiredSkillRepository.findTopSkillTypesByPositionEscapeSkillType(positionId, exceptedSkillTypeId);
    }

    public List<SkillPredictionModel> findHistoricalDataByPositionIdAndSkillId(int positionId, int skillId) {
        LocalDate currentDay = LocalDate.now();

        List<SkillPredictionModel> historicalData = new ArrayList<>();

        for (int i = 3; i >= 0; i--) {
            int month = currentDay.minusMonths(i).getMonthValue();
            int year = currentDay.minusMonths(i).getYear();

            SkillPredictionModel predictionModel =
                    findTotalRecruitmentByPositionIdAndSkillIdAndMonthYear(month, year, positionId, skillId);
            historicalData.add(predictionModel);
        }

        return historicalData;
    }

    private SkillPredictionModel findTotalRecruitmentByPositionIdAndSkillIdAndMonthYear(int month, int year,
                                                                                        int positionId, int skillId) {
        Integer totalRecruitment = requiredSkillRepository
                .findTotalRecruitmentByPositionIdAndSkillIdAndMonthYear(positionId, skillId, month, year);
        return new SkillPredictionModel(month, totalRecruitment);
    }

    public List<Integer> findTopSkillsByPositionId(Integer positionId, Integer top) {
        return requiredSkillRepository.findTopSkillsByPositionId(positionId, top);
    }
}
