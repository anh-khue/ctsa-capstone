package io.ctsa.companymanagement.repository;

import io.ctsa.companymanagement.model.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Integer> {
    List<Recruitment> findAllByCompanyId(int companyId);
}
