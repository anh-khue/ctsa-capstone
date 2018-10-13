package io.ctsa.careersservice.service;

import io.ctsa.careersservice.model.Skill;
import io.ctsa.careersservice.repository.SkillRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    private final KeywordService keywordService;

    public SkillService(SkillRepository skillRepository,
                        KeywordService keywordService) {
        this.skillRepository = skillRepository;
        this.keywordService = keywordService;
    }

    @Transactional
    public void insertSkill(Skill skill) throws Exception {
        skillRepository.save(skill);
        skillRepository.flush();

        keywordService.insertKeyword(skill.getName()
                                          .toLowerCase(), null,
                                     null, skill.getId());
    }

    public Optional<Skill> findById(int id) {
        return skillRepository.findById(id);
    }
}
