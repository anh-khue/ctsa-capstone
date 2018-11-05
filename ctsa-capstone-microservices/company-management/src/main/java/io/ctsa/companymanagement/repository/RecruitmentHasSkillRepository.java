package io.ctsa.companymanagement.repository;

import io.ctsa.companymanagement.model.RecruitmentHasSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruitmentHasSkillRepository extends JpaRepository<RecruitmentHasSkill, Integer> {
    List<RecruitmentHasSkill> findByRecruitmentId(int id);
}
