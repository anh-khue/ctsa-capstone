package io.ctsa.resultssuggestionsservice.service;

import io.ctsa.resultssuggestionsservice.model.HighSchoolSubject;
import io.ctsa.resultssuggestionsservice.repository.HighSchoolSubjectRepository;
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
