package io.ctsa.careerssuggestionsservice.repository.centroid;

import io.ctsa.careerssuggestionsservice.model.centroid.HighSchoolTopCentroid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HighSchoolTopCentroidRepository extends JpaRepository<HighSchoolTopCentroid, Integer> {

    List<HighSchoolTopCentroid> findByMajorCentroidId(int majorId);
}
