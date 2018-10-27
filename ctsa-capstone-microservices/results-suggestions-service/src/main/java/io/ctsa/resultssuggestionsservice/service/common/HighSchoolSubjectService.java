package io.ctsa.resultssuggestionsservice.service.common;

import io.ctsa.resultssuggestionsservice.model.common.HighSchoolSubject;
import io.ctsa.resultssuggestionsservice.repository.common.HighSchoolSubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HighSchoolSubjectService {

    private final HighSchoolSubjectRepository highSchoolSubjectRepository;

    public HighSchoolSubjectService(HighSchoolSubjectRepository highSchoolSubjectRepository) {
        this.highSchoolSubjectRepository = highSchoolSubjectRepository;
    }

    public List<HighSchoolSubject> getAll() {
        return highSchoolSubjectRepository.findAll();
    }
}
