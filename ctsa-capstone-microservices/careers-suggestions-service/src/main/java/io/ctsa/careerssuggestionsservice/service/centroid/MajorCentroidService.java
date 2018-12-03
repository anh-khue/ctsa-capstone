package io.ctsa.careerssuggestionsservice.service.centroid;

import io.ctsa.careerssuggestionsservice.exception.MajorCentroidNotFoundException;
import io.ctsa.careerssuggestionsservice.model.centroid.MajorCentroid;
import io.ctsa.careerssuggestionsservice.repository.centroid.MajorCentroidRepository;
import org.springframework.stereotype.Service;

@Service
public class MajorCentroidService {

    private final MajorCentroidRepository majorCentroidRepository;

    public MajorCentroidService(MajorCentroidRepository majorCentroidRepository) {
        this.majorCentroidRepository = majorCentroidRepository;
    }

    public MajorCentroid getById(int id)
            throws MajorCentroidNotFoundException {
        return majorCentroidRepository.findById(id)
                                      .orElseThrow(MajorCentroidNotFoundException::new);
    }
}
