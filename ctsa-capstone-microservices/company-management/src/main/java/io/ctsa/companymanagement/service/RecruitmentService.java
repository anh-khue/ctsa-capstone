package io.ctsa.companymanagement.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ctsa.companymanagement.exception.RecruitmentNotFoundException;
import io.ctsa.companymanagement.model.Recruitment;
import io.ctsa.companymanagement.model.RecruitmentHasSkill;
import io.ctsa.companymanagement.repository.RecruitmentHasSkillRepository;
import io.ctsa.companymanagement.repository.RecruitmentRepository;
import io.ctsa.companymanagement.stream.payload.warehouse.RecruitmentMessagePayload;
import io.ctsa.companymanagement.stream.producer.StreamProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final RecruitmentHasSkillRepository recruitmentHasSkillRepository;

    private final StreamProducer<RecruitmentMessagePayload> streamProducer;

    public RecruitmentService(RecruitmentRepository recruitmentRepository,
                              RecruitmentHasSkillRepository recruitmentHasSkillRepository,
                              StreamProducer<RecruitmentMessagePayload> streamProducer) {
        this.recruitmentRepository = recruitmentRepository;
        this.recruitmentHasSkillRepository = recruitmentHasSkillRepository;
        this.streamProducer = streamProducer;
    }

    public List<Recruitment> getAll() {
        return recruitmentRepository.findAll();
    }

    public Page<Recruitment> getAllByPage(int pageNumber, int itemsPerPage) {
        Pageable pageable = PageRequest.of(pageNumber - 1, itemsPerPage);
        Page<Recruitment> list = recruitmentRepository.findByPublished(pageable, 1);
        for (Recruitment recruitment : list) {
            recruitment.setSkills(getRecruitmentSkills(recruitment.getId()));
        }
        return list;
    }

    private Map<String, String> convertJsonToMap(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<Map<String, String>>() {
        });
    }

    private List<RecruitmentHasSkill> getRecruitmentSkills(int recruitmentId) {
        return recruitmentHasSkillRepository.findByRecruitmentId(recruitmentId);
    }

    public Optional<Recruitment> getById(int recruitmentId) {
        return recruitmentRepository.findById(recruitmentId)
                                    .map(recruitment -> {
                                        recruitment.setSkills(getRecruitmentSkills(recruitment.getId()));
                                        try {
                                            recruitment.setAttributes(convertJsonToMap(recruitment.getAdditionalInformation()));
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                        return recruitment;
                                    });
    }

    public Recruitment create(Recruitment recruitment) {
        try {
//            recruitmentRepository.saveAndFlush(recruitment);
            streamProducer.sendMessage(new RecruitmentMessagePayload(recruitment));

            return recruitment;
        } catch (Exception e) {
            log.error("Error creating new recruitment.");

            return null;
        }
    }

    public Recruitment updateStatus(int recruitmentId, int status) throws RecruitmentNotFoundException {
        return recruitmentRepository.findById(recruitmentId)
                                    .map(recruitment -> {
                                        recruitment.setPublished(status);
                                        return recruitment;
                                    }).orElseThrow(RecruitmentNotFoundException::new);
    }

    public Recruitment updateRecruitment(int recruitmentId, Recruitment modifiedData) throws RecruitmentNotFoundException {
        return recruitmentRepository.findById(recruitmentId)
                                    .map(recruitment -> {
                                        recruitment.setTitle(modifiedData.getTitle());
                                        recruitment.setPositionId(modifiedData.getPositionId());
                                        recruitment.setPositionName(modifiedData.getPositionName());
                                        recruitment.setEndDate(modifiedData.getEndDate());
                                        recruitment.setModifiedDate(new Timestamp(System.currentTimeMillis()));
                                        recruitment.setNumber(modifiedData.getNumber());
                                        recruitment.setJobDescription(modifiedData.getJobDescription());
                                        recruitment.setJobRequirement(modifiedData.getJobRequirement());
//                    recruitment.setPublished(modifiedData.getPublished());
                                        recruitment.setEmail(modifiedData.getEmail());
                                        recruitment.setPhone(modifiedData.getPhone());
                                        recruitment.setAddress(modifiedData.getAddress());
                                        recruitment.setAdditionalInformation(modifiedData.getAdditionalInformation());
//                    recruitment.setImage(modifiedData.getImage());

                                        saveListSkills(recruitment.getSkills());

                                        recruitmentRepository.saveAndFlush(recruitment);

                                        return recruitment;
                                    })
                                    .orElseThrow(RecruitmentNotFoundException::new);
    }

    private void saveListSkills(List<RecruitmentHasSkill> list) {
        for (RecruitmentHasSkill skill : list) {
            if (recruitmentHasSkillRepository.countByRecruitmentIdAndSkillId(skill.getRecruitmentId(), skill.getSkillId()) != 0) {
                list.remove(skill);
            }
        }
        recruitmentHasSkillRepository.saveAll(list);
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

    public Page<Recruitment> getByCompanyIdAndPublished(int companyId, int status, int pageNumber, int itemsPerPage) {
        Pageable pageable = PageRequest.of(pageNumber - 1, itemsPerPage);
        return recruitmentRepository.findByCompanyIdAndPublished(pageable, companyId, status);
    }
}
