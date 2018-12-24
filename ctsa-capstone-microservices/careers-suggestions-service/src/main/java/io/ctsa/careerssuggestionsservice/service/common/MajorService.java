package io.ctsa.careerssuggestionsservice.service.common;

import io.ctsa.careerssuggestionsservice.model.common.Major;
import io.ctsa.careerssuggestionsservice.repository.common.MajorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MajorService {
    private MajorRepository majorRepository;

    public MajorService(MajorRepository majorRepository) {
        this.majorRepository = majorRepository;
    }

    public List<Major> getAll() {
        return majorRepository.findAll();
    }

    public Optional<Major> getById(int majorId) {
        return majorRepository.findById(majorId);
    }
}
