package io.ctsa.companymanagement.repository;

import io.ctsa.companymanagement.model.Recruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Integer> {
    Page<Recruitment> findByPublished(Pageable pageable, int published);

    Page<Recruitment> findByCompanyId(Pageable pageable, int companyId);

    Page<Recruitment> findByCompanyIdAndPublished(Pageable pageable, int companyId, int published);

//    @Query("select r.published from Recruitment r where r.id = :id")
//    boolean isPublished(@Param("id") int recruitmentId);
}
