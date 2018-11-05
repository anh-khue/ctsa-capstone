package io.ctsa.warehouseservice.service;

import io.ctsa.warehouseservice.model.RequiredSkill;
import io.ctsa.warehouseservice.repository.RequiredSkillRepository;
import org.springframework.stereotype.Service;

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

    public List<Integer> findTopSkillsByPositionAndSkillType(Integer positionId,
                                                             Integer skillTypeId,
                                                             Integer page,
                                                             Integer skillsPerPage) {
        return requiredSkillRepository.findTopSkillsByPositionAndSkillType(positionId, skillTypeId,
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
}
