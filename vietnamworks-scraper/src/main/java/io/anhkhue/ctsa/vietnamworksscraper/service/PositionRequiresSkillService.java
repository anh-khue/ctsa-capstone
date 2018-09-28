package io.anhkhue.ctsa.vietnamworksscraper.service;

import io.anhkhue.ctsa.vietnamworksscraper.repository.PositionRequiresSkillRepository;
import org.springframework.stereotype.Service;

@Service
public class PositionRequiresSkillService {

    private final PositionRequiresSkillRepository positionRequiresSkillRepository;

    public PositionRequiresSkillService(PositionRequiresSkillRepository positionRequiresSkillRepository) {
        this.positionRequiresSkillRepository = positionRequiresSkillRepository;
    }
}
