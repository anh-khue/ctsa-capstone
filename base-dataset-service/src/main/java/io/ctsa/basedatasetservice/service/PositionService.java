package io.ctsa.basedatasetservice.service;

import io.ctsa.basedatasetservice.model.Position;
import io.ctsa.basedatasetservice.repository.PositionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PositionService {

    private final PositionRepository positionRepository;

    private final KeywordService keywordService;

    public PositionService(PositionRepository positionRepository,
                           KeywordService keywordService) {
        this.positionRepository = positionRepository;
        this.keywordService = keywordService;
    }

    @Transactional
    public void insertPosition(Position position)
            throws Exception {
        positionRepository.save(position);
        positionRepository.flush();

        keywordService.insertKeyword(position.getName()
                                             .toLowerCase(), null,
                                     position.getId(), null);
    }
}
