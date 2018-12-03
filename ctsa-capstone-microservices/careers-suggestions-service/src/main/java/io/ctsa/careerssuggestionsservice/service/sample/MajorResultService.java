package io.ctsa.careerssuggestionsservice.service.sample;

import io.ctsa.careerssuggestionsservice.exception.MajorResultNotFoundException;
import io.ctsa.careerssuggestionsservice.model.sample.MajorSample;
import io.ctsa.careerssuggestionsservice.repository.sample.MajorSampleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorResultService {

    private final MajorSampleRepository majorSampleRepository;

    public MajorResultService(MajorSampleRepository majorSampleRepository) {
        this.majorSampleRepository = majorSampleRepository;
    }

    public MajorSample getById(int id)
            throws MajorResultNotFoundException {
        return majorSampleRepository.findById(id)
                                    .orElseThrow(MajorResultNotFoundException::new);
    }

    public List<MajorSample> getByMajorId(int majorId) {
        return majorSampleRepository.findByMajorId(majorId);
    }
}
