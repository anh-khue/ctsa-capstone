package io.ctsa.careerssuggestionsservice.repository.centroid;

import io.ctsa.careerssuggestionsservice.model.centroid.MajorCentroid;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MajorCentroidRepository extends JpaRepository<MajorCentroid, Integer> {

    MajorCentroid findByMajorId(int majorId);
}
