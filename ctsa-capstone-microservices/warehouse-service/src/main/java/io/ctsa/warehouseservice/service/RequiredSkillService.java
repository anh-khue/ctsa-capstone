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

    public List<Integer> findTopSkillsByPositionAndSkillType(Integer positionId, Integer skillTypeId) {
        return requiredSkillRepository.findTopSkillsByPositionAndSkillType(positionId, skillTypeId);
    }
}
