package io.ctsa.resultssuggestionsservice.service.centroid;

import io.ctsa.resultssuggestionsservice.model.centroid.EntranceExamCentroid;
import io.ctsa.resultssuggestionsservice.repository.centroid.EntranceExamCentroidRepository;
import org.springframework.stereotype.Service;

@Service
public class EntranceExamResultCentroidService {

    private final EntranceExamCentroidRepository entranceExamCentroidRepository;

    public EntranceExamResultCentroidService(EntranceExamCentroidRepository entranceExamCentroidRepository) {
        this.entranceExamCentroidRepository = entranceExamCentroidRepository;
    }


    public int save(EntranceExamCentroid centroid) {
        entranceExamCentroidRepository.saveAndFlush(centroid);
        return centroid.getId();
    }
}
