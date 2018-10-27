package io.ctsa.resultssuggestionsservice.service.centroid;

import io.ctsa.resultssuggestionsservice.exception.EntranceExamResultNotFoundException;
import io.ctsa.resultssuggestionsservice.exception.MajorCentroidNotFoundException;
import io.ctsa.resultssuggestionsservice.exception.MajorNotFoundException;
import io.ctsa.resultssuggestionsservice.model.centroid.MajorCentroid;
import io.ctsa.resultssuggestionsservice.repository.centroid.EntranceExamCentroidRepository;
import io.ctsa.resultssuggestionsservice.repository.centroid.HighSchoolTopResultCentroidRepository;
import io.ctsa.resultssuggestionsservice.repository.centroid.MajorCentroidRepository;
import io.ctsa.resultssuggestionsservice.repository.common.MajorRepository;
import org.springframework.stereotype.Service;

@Service
public class MajorCentroidService {

    private final MajorCentroidRepository majorCentroidRepository;

    private final EntranceExamCentroidRepository entranceExamCentroidRepository;
    private final MajorRepository majorRepository;

    private final HighSchoolTopResultCentroidRepository highSchoolTopResultCentroidRepository;

    public MajorCentroidService(MajorCentroidRepository majorCentroidRepository,
                                EntranceExamCentroidRepository entranceExamCentroidRepository,
                                MajorRepository majorRepository,
                                HighSchoolTopResultCentroidRepository highSchoolTopResultCentroidRepository) {
        this.majorCentroidRepository = majorCentroidRepository;
        this.entranceExamCentroidRepository = entranceExamCentroidRepository;
        this.majorRepository = majorRepository;
        this.highSchoolTopResultCentroidRepository = highSchoolTopResultCentroidRepository;
    }

    public MajorCentroid getById(int id)
            throws MajorCentroidNotFoundException,
                   MajorNotFoundException,
                   EntranceExamResultNotFoundException {
        return getMajorCentroid(majorCentroidRepository.findById(id)
                                                       .orElseThrow(MajorCentroidNotFoundException::new));
    }

    private MajorCentroid getMajorCentroid(MajorCentroid majorResult)
            throws MajorNotFoundException,
                   EntranceExamResultNotFoundException {
        return getCollectionOrm(getSingleOrm(majorResult));
    }

    private MajorCentroid getSingleOrm(MajorCentroid majorCentroid)
            throws MajorNotFoundException,
                   EntranceExamResultNotFoundException {
        majorCentroid.setEntranceExamCentroid(entranceExamCentroidRepository
                                                            .findById(majorCentroid.getEntranceExamCentroidId())
                                                            .orElseThrow(EntranceExamResultNotFoundException::new));
        majorCentroid.setMajor(majorRepository.findById(majorCentroid.getMajorId())
                                              .orElseThrow(MajorNotFoundException::new));

        return majorCentroid;
    }

    private MajorCentroid getCollectionOrm(MajorCentroid majorCentroid) {
        majorCentroid.setHighSchoolTopCentroids(highSchoolTopResultCentroidRepository
                                                              .findByMajorCentroidId(majorCentroid.getId()));

        return majorCentroid;
    }
}
