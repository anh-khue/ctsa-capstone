package io.ctsa.careerssuggestionsservice.service.common;

import io.ctsa.careerssuggestionsservice.model.common.EntranceExamSubject;
import io.ctsa.careerssuggestionsservice.repository.common.EntranceExamSubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntranceExamSubjectService {

    private final EntranceExamSubjectRepository entranceExamSubjectRepository;

    public EntranceExamSubjectService(EntranceExamSubjectRepository entranceExamSubjectRepository) {
        this.entranceExamSubjectRepository = entranceExamSubjectRepository;
    }

    public List<EntranceExamSubject> getAll() {
        return entranceExamSubjectRepository.findAll();
    }
}
