package io.ctsa.careersservice.service;

import io.ctsa.careersservice.model.SkillType;
import io.ctsa.careersservice.repository.SkillTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillTypeService {

    private final SkillTypeRepository skillTypeRepository;

    public SkillTypeService(SkillTypeRepository skillTypeRepository) {
        this.skillTypeRepository = skillTypeRepository;
    }

    public List<SkillType> findByBusinessFieldId(int businessFieldId) {
        return skillTypeRepository.findByBusinessFieldId(businessFieldId);
    }
}
