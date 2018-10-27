package io.ctsa.resultssuggestionsservice.service.sample;

import io.ctsa.resultssuggestionsservice.exception.EntranceExamResultNotFoundException;
import io.ctsa.resultssuggestionsservice.exception.MajorNotFoundException;
import io.ctsa.resultssuggestionsservice.exception.MajorResultNotFoundException;
import io.ctsa.resultssuggestionsservice.model.sample.EntranceExamSample;
import io.ctsa.resultssuggestionsservice.model.sample.MajorSample;
import io.ctsa.resultssuggestionsservice.repository.common.MajorRepository;
import io.ctsa.resultssuggestionsservice.repository.common.PersonRepository;
import io.ctsa.resultssuggestionsservice.repository.sample.EntranceExamSampleRepository;
import io.ctsa.resultssuggestionsservice.repository.sample.HighSchoolTopSampleRepository;
import io.ctsa.resultssuggestionsservice.repository.sample.MajorSampleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MajorResultService {

    private final MajorSampleRepository majorSampleRepository;

    private final PersonRepository personRepository;
    private final EntranceExamSampleRepository entranceExamSampleRepository;
    private final MajorRepository majorRepository;

    private final HighSchoolTopSampleRepository highSchoolTopSampleRepository;

    public MajorResultService(MajorSampleRepository majorSampleRepository,
                              PersonRepository personRepository,
                              EntranceExamSampleRepository entranceExamSampleRepository,
                              MajorRepository majorRepository,
                              HighSchoolTopSampleRepository highSchoolTopSampleRepository) {
        this.majorSampleRepository = majorSampleRepository;
        this.personRepository = personRepository;
        this.entranceExamSampleRepository = entranceExamSampleRepository;
        this.majorRepository = majorRepository;
        this.highSchoolTopSampleRepository = highSchoolTopSampleRepository;
    }

    public MajorSample getById(int id)
            throws MajorResultNotFoundException,
                   MajorNotFoundException,
                   EntranceExamResultNotFoundException {
        return getMajorResult(majorSampleRepository.findById(id)
                                                   .orElseThrow(MajorResultNotFoundException::new));
    }

    public List<MajorSample> getByMajorId(int majorId) {
        return majorSampleRepository.findByMajorId(majorId)
                                    .stream()
                                    .map(this::getMajorResult)
                                    .filter(majorSample -> majorSample.getMajor() != null)
                                    .collect(Collectors.toList());
    }

    private MajorSample getMajorResult(MajorSample majorSample) {
        return getCollectionOrm(getSingleOrm(majorSample));
    }

    private MajorSample getSingleOrm(MajorSample majorSample) {
        if (majorSample.getPersonId() != null) {
            majorSample.setPerson(personRepository.findById(majorSample.getPersonId())
                                                  .orElse(null));
        }
        majorSample.setEntranceExamSample(entranceExamSampleRepository.findById(majorSample.getEntranceExamSampleId())
                                                                      .orElse(new EntranceExamSample()));
        majorSample.setMajor(majorRepository.findById(majorSample.getMajorId())
                                            .orElse(null));

        return majorSample;
    }

    private MajorSample getCollectionOrm(MajorSample majorSample) {
        majorSample.setHighSchoolTopSamples(highSchoolTopSampleRepository.findByMajorSampleId(majorSample.getId()));

        return majorSample;
    }
}
