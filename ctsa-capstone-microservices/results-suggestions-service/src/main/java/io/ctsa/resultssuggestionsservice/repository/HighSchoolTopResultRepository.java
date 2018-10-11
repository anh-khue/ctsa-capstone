package io.ctsa.resultssuggestionsservice.repository;

import io.ctsa.resultssuggestionsservice.model.HighSchoolTopResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HighSchoolTopResultRepository extends JpaRepository<HighSchoolTopResult, Integer> {

    List<HighSchoolTopResult> findByMajorResultId(int majorId);
}
