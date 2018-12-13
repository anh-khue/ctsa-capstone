package io.ctsa.careertrendservice.repository;

import io.ctsa.careertrendservice.model.SupportingInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupportingInformationRepository extends JpaRepository<SupportingInformation, Integer> {

    List<SupportingInformation> findByYear(int year);

    List<SupportingInformation> findByMajorIdOrderByYearAsc(int majorId);

    Page<SupportingInformation> findByMajorIdOrderByYearAsc(int majorId, Pageable pageable);

    SupportingInformation findFirstByMajorIdOrderByYearDesc(int majorId);

    List<SupportingInformation> findByMajorId(int majorId);
}
