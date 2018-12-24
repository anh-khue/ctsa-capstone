package io.ctsa.careerssuggestionsservice.service.centroid;

import io.ctsa.careerssuggestionsservice.model.centroid.EntranceExamCentroidDetail;
import io.ctsa.careerssuggestionsservice.repository.centroid.EntranceExamCentroidDetailRepository;
import org.springframework.stereotype.Service;

@Service
public class EntranceExamResultCentroidService {

    private final EntranceExamCentroidDetailRepository entranceExamCentroidDetailRepository;

    public EntranceExamResultCentroidService(EntranceExamCentroidDetailRepository entranceExamCentroidDetailRepository) {
        this.entranceExamCentroidDetailRepository = entranceExamCentroidDetailRepository;
    }


    public int save(EntranceExamCentroidDetail centroid) {
        entranceExamCentroidDetailRepository.saveAndFlush(centroid);
        return centroid.getId();
    }
}
