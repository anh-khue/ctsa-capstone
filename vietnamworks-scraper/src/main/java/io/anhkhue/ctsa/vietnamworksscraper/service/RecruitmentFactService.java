package io.anhkhue.ctsa.vietnamworksscraper.service;

import io.anhkhue.ctsa.vietnamworksscraper.repository.RecruitmentFactRepository;
import org.springframework.stereotype.Service;

@Service
public class RecruitmentFactService {

    private final RecruitmentFactRepository recruitmentFactRepository;

    public RecruitmentFactService(RecruitmentFactRepository recruitmentFactRepository) {
        this.recruitmentFactRepository = recruitmentFactRepository;
    }
}
