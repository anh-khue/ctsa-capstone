package io.ctsa.careertrendservice.repository;

import io.ctsa.careertrendservice.model.HumanResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HumanResourcesRepository extends JpaRepository<HumanResource, Integer> {

    List<HumanResource> findByYear(int year);

    List<HumanResource> findByMajorIdOrderByYearAsc(int majorId);

    HumanResource findFirstByMajorIdOrderByYearDesc(int majorId);
}
