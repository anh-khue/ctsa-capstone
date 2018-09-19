package io.ctsa.basedatasetservice.repository;

import io.ctsa.basedatasetservice.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Integer> {

}
