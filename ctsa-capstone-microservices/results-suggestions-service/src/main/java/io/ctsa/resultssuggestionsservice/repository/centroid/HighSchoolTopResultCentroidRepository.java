package io.ctsa.resultssuggestionsservice.repository.centroid;

import io.ctsa.resultssuggestionsservice.model.centroid.HighSchoolTopCentroid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HighSchoolTopResultCentroidRepository extends JpaRepository<HighSchoolTopCentroid, Integer> {

    List<HighSchoolTopCentroid> findByMajorCentroidId(int majorId);
}
