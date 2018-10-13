package io.ctsa.warehouseservice.service;

import io.ctsa.warehouseservice.model.Recruitment;
import io.ctsa.warehouseservice.repository.RecruitmentRepository;
import org.springframework.stereotype.Service;

@Service
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

    public RecruitmentService(RecruitmentRepository recruitmentRepository) {
        this.recruitmentRepository = recruitmentRepository;
    }

    public Recruitment saveRecruitment(Recruitment recruitment) {
        recruitmentRepository.save(recruitment);
        recruitmentRepository.flush();
        return recruitment;
    }
}
