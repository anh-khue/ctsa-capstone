package io.anhkhue.ctsa.vietnamworksscraper.repository;

import io.anhkhue.ctsa.vietnamworksscraper.model.PositionRequiresSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PositionRequiresSkillRepository extends JpaRepository<PositionRequiresSkill, Integer> {

    Optional<PositionRequiresSkill> findByPositionIdAndSkillId(int positionId, int skillId);

}
