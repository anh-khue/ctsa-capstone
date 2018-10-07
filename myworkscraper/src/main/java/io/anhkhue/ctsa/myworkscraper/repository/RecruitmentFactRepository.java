package io.anhkhue.ctsa.myworkscraper.repository;

import io.anhkhue.ctsa.myworkscraper.model.RecruitmentFact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentFactRepository extends JpaRepository<RecruitmentFact, Integer> {
}
