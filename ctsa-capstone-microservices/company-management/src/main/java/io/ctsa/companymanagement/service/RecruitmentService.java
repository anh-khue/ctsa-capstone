package io.ctsa.companymanagement.service;

import io.ctsa.companymanagement.exception.RecruitmentNotFoundException;
import io.ctsa.companymanagement.model.Recruitment;
import io.ctsa.companymanagement.repository.RecruitmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

    public RecruitmentService(RecruitmentRepository recruitmentRepository) {
        this.recruitmentRepository = recruitmentRepository;
    }

    public List<Recruitment> getAll() {
        return recruitmentRepository.findAll();
    }

    public Optional<Recruitment> getById(int recruitmentId) {
        return recruitmentRepository.findById(recruitmentId);
    }

    public Recruitment create(Recruitment recruitment) {
        try {
            recruitmentRepository.saveAndFlush(recruitment);

            return recruitment;
        } catch (Exception e) {
            log.error("Error creating new recruitment.");

            return null;
        }
    }

    public Recruitment updateRecruitment(int recruitmentId, Recruitment modifiedData) throws RecruitmentNotFoundException {
        return recruitmentRepository.findById(recruitmentId)
                .map(recruitment -> {
                    recruitment.setTitle(modifiedData.getTitle());
                    recruitment.setPosition(modifiedData.getPosition());
                    recruitment.setEndDate(modifiedData.getEndDate());
                    recruitment.setModifiedDate(new Timestamp(System.currentTimeMillis()));
                    recruitment.setNumber(modifiedData.getNumber());
                    recruitment.setJobDescription(modifiedData.getJobDescription());
                    recruitment.setJobRequirement(modifiedData.getJobRequirement());
                    recruitment.setEnabled(modifiedData.getEnabled());
                    recruitment.setEmail(modifiedData.getEmail());
                    recruitment.setPhone(modifiedData.getPhone());
                    recruitment.setAddress(modifiedData.getAddress());
                    recruitment.setAdditionalInformation(modifiedData.getAdditionalInformation());
                    recruitment.setImage(modifiedData.getImage());
                    recruitmentRepository.saveAndFlush(recruitment);

                    return recruitment;
                })
                .orElseThrow(RecruitmentNotFoundException::new);
    }

    public void updateViewCount(int recruitmentId) throws RecruitmentNotFoundException {
        recruitmentRepository.findById(recruitmentId)
                .map(recruitment -> {
                    recruitment.setViewCount(recruitment.getViewCount() + 1);
                    recruitmentRepository.saveAndFlush(recruitment);

                    return recruitment;
                })
                .orElseThrow(RecruitmentNotFoundException::new);
    }
}
