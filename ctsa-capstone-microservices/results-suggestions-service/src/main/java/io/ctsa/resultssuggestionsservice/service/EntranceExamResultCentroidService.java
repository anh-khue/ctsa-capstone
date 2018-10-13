package io.ctsa.resultssuggestionsservice.service;

import io.ctsa.resultssuggestionsservice.model.EntranceExamResultCentroid;
import io.ctsa.resultssuggestionsservice.repository.EntranceExamResultCentroidRepository;
import org.springframework.stereotype.Service;

@Service
public class EntranceExamResultCentroidService {

    private final EntranceExamResultCentroidRepository entranceExamResultCentroidRepository;

    public EntranceExamResultCentroidService(EntranceExamResultCentroidRepository entranceExamResultCentroidRepository) {
        this.entranceExamResultCentroidRepository = entranceExamResultCentroidRepository;
    }


    public int save(EntranceExamResultCentroid centroid) {
        entranceExamResultCentroidRepository.saveAndFlush(centroid);
        return centroid.getId();
    }
}
