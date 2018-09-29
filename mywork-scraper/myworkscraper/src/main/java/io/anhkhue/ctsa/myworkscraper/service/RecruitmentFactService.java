package io.anhkhue.ctsa.myworkscraper.service;

import io.anhkhue.ctsa.myworkscraper.repository.RecruitmentFactRepository;
import org.springframework.stereotype.Service;

@Service
public class RecruitmentFactService {

    private final RecruitmentFactRepository recruitmentFactRepository;

    public RecruitmentFactService(RecruitmentFactRepository recruitmentFactRepository) {
        this.recruitmentFactRepository = recruitmentFactRepository;
    }
}
