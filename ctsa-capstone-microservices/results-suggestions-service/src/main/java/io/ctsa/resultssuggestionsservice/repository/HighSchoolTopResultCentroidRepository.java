package io.ctsa.resultssuggestionsservice.repository;

import io.ctsa.resultssuggestionsservice.model.HighSchoolTopResultCentroid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HighSchoolTopResultCentroidRepository extends JpaRepository<HighSchoolTopResultCentroid, Integer> {

    List<HighSchoolTopResultCentroid> findByMajorCentroidId(int majorId);
}
