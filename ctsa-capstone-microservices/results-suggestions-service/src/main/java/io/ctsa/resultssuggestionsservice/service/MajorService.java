package io.ctsa.resultssuggestionsservice.service;

import io.ctsa.resultssuggestionsservice.model.Major;
import io.ctsa.resultssuggestionsservice.repository.MajorRepository;
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
