package io.ctsa.resultssuggestionsservice.repository.centroid;

import io.ctsa.resultssuggestionsservice.model.centroid.EntranceExamCentroid;
import io.ctsa.resultssuggestionsservice.model.centroid.EntranceExamCentroidDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntranceExamCentroidDetailRepository extends JpaRepository<EntranceExamCentroidDetail, Integer> {

}
