package io.ctsa.resultssuggestionsservice.service;

import io.ctsa.resultssuggestionsservice.exception.EntranceExamResultNotFoundException;
import io.ctsa.resultssuggestionsservice.exception.MajorCentroidNotFoundException;
import io.ctsa.resultssuggestionsservice.exception.MajorNotFoundException;
import io.ctsa.resultssuggestionsservice.model.MajorCentroid;
import io.ctsa.resultssuggestionsservice.repository.*;
import org.springframework.stereotype.Service;

@Service
public class MajorCentroidService {

    private final MajorCentroidRepository majorCentroidRepository;

    private final EntranceExamResultCentroidRepository entranceExamResultCentroidRepository;
    private final MajorRepository majorRepository;

    private final HighSchoolTopResultCentroidRepository highSchoolTopResultCentroidRepository;

    public MajorCentroidService(MajorCentroidRepository majorCentroidRepository,
                                EntranceExamResultCentroidRepository entranceExamResultCentroidRepository,
                                MajorRepository majorRepository,
                                HighSchoolTopResultCentroidRepository highSchoolTopResultCentroidRepository) {
        this.majorCentroidRepository = majorCentroidRepository;
        this.entranceExamResultCentroidRepository = entranceExamResultCentroidRepository;
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
        majorCentroid.setEntranceExamResultCentroid(entranceExamResultCentroidRepository
                                                            .findById(majorCentroid.getEntranceExamResultCentroidId())
                                                            .orElseThrow(EntranceExamResultNotFoundException::new));
        majorCentroid.setMajor(majorRepository.findById(majorCentroid.getMajorId())
                                              .orElseThrow(MajorNotFoundException::new));

        return majorCentroid;
    }

    private MajorCentroid getCollectionOrm(MajorCentroid majorCentroid) {
        majorCentroid.setHighSchoolTopResultCentroids(highSchoolTopResultCentroidRepository
                                                              .findByMajorCentroidId(majorCentroid.getId()));

        return majorCentroid;
    }
}
