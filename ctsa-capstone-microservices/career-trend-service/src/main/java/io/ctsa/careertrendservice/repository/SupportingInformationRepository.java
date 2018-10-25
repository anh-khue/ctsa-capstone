package io.ctsa.careertrendservice.repository;

import io.ctsa.careertrendservice.model.SupportingInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupportingInformationRepository extends JpaRepository<SupportingInformation, Integer> {

    List<SupportingInformation> findAllByYear(int year);

    List<SupportingInformation> findAllByMajorIdOrderByYearAsc(int majorId);

    SupportingInformation findFirstByMajorIdOrderByYearDesc(int majorId);

    List<SupportingInformation> findByMajorId(int majorId);
}
