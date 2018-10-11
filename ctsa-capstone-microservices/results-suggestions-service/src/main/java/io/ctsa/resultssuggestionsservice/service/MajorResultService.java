package io.ctsa.resultssuggestionsservice.service;

import io.ctsa.resultssuggestionsservice.exception.EntranceExamResultNotFoundException;
import io.ctsa.resultssuggestionsservice.exception.MajorNotFoundException;
import io.ctsa.resultssuggestionsservice.exception.MajorResultNotFoundException;
import io.ctsa.resultssuggestionsservice.model.EntranceExamResult;
import io.ctsa.resultssuggestionsservice.model.MajorResult;
import io.ctsa.resultssuggestionsservice.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MajorResultService {

    private final MajorResultRepository majorResultRepository;

    private final PersonRepository personRepository;
    private final EntranceExamResultRepository entranceExamResultRepository;
    private final MajorRepository majorRepository;

    private final HighSchoolTopResultRepository highSchoolTopResultRepository;

    public MajorResultService(MajorResultRepository majorResultRepository,
                              PersonRepository personRepository,
                              EntranceExamResultRepository entranceExamResultRepository,
                              MajorRepository majorRepository,
                              HighSchoolTopResultRepository highSchoolTopResultRepository) {
        this.majorResultRepository = majorResultRepository;
        this.personRepository = personRepository;
        this.entranceExamResultRepository = entranceExamResultRepository;
        this.majorRepository = majorRepository;
        this.highSchoolTopResultRepository = highSchoolTopResultRepository;
    }

    public MajorResult getById(int id)
            throws MajorResultNotFoundException,
                   MajorNotFoundException,
                   EntranceExamResultNotFoundException {
        return getMajorResult(majorResultRepository.findById(id)
                                                   .orElseThrow(MajorResultNotFoundException::new));
    }

    public List<MajorResult> getByMajorId(int majorId) {
        return majorResultRepository.findByMajorId(majorId)
                                    .stream()
                                    .map(this::getMajorResult)
                                    .filter(majorResult -> majorResult.getMajor() != null)
                                    .collect(Collectors.toList());
    }

    private MajorResult getMajorResult(MajorResult majorResult) {
        return getCollectionOrm(getSingleOrm(majorResult));
    }

    private MajorResult getSingleOrm(MajorResult majorResult) {
        majorResult.setPerson(personRepository.findById(majorResult.getPersonId())
                                              .orElse(null));
        majorResult.setEntranceExamResult(entranceExamResultRepository.findById(majorResult.getEntranceExamResultId())
                                                                      .orElse(new EntranceExamResult()));
        majorResult.setMajor(majorRepository.findById(majorResult.getMajorId())
                                            .orElse(null));

        return majorResult;
    }

    private MajorResult getCollectionOrm(MajorResult majorResult) {
        majorResult.setHighSchoolTopResults(highSchoolTopResultRepository.findByMajorResultId(majorResult.getId()));

        return majorResult;
    }
}
