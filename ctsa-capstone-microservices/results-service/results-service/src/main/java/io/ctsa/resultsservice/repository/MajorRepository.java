package io.ctsa.resultsservice.repository;

import io.ctsa.resultsservice.model.Major;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MajorRepository extends JpaRepository<Major, Integer> {

}
