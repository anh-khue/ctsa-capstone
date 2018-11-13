package io.ctsa.careertrendservice.service;

import io.ctsa.careertrendservice.model.MajorStandard;
import io.ctsa.careertrendservice.repository.MajorStandardRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MajorStandardService {

    private final MajorStandardRepository majorStandardRepository;

    public MajorStandardService(MajorStandardRepository majorStandardRepository) {
        this.majorStandardRepository = majorStandardRepository;
    }

    public Optional<MajorStandard> findByMajorId(Integer majorId) {
        return majorStandardRepository.findByMajorId(majorId);
    }
}
