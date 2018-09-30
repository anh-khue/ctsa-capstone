package io.ctsa.careersservice.repository;

import io.ctsa.careersservice.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Integer> {

}
