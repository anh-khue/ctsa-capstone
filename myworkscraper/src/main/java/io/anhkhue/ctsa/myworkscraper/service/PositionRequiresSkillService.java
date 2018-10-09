package io.anhkhue.ctsa.myworkscraper.service;

import io.anhkhue.ctsa.myworkscraper.model.PositionRequiresSkill;
import io.anhkhue.ctsa.myworkscraper.repository.PositionRequiresSkillRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PositionRequiresSkillService {

    private final PositionRequiresSkillRepository positionRequiresSkillRepository;

    public PositionRequiresSkillService(PositionRequiresSkillRepository positionRequiresSkillRepository) {
        this.positionRequiresSkillRepository = positionRequiresSkillRepository;
    }

    public Optional<PositionRequiresSkill> findByPositionIdAndSkillId(int positionId, int skillId) {
        return positionRequiresSkillRepository.findByPositionIdAndSkillId(positionId, skillId);
    }

    public PositionRequiresSkill create(PositionRequiresSkill positionRequiresSkill) {
        positionRequiresSkillRepository.save(positionRequiresSkill);
        positionRequiresSkillRepository.flush();
        return positionRequiresSkill;
    }
}
