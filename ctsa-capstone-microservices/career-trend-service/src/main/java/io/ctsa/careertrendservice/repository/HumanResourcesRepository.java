package io.ctsa.careertrendservice.repository;

import io.ctsa.careertrendservice.model.HumanResources;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HumanResourcesRepository extends JpaRepository<HumanResources, Integer> {
    List<HumanResources> findAllByYear(int year);
    List<HumanResources> findAllByCareerIdOrderByYearAsc(int careerId);
}
