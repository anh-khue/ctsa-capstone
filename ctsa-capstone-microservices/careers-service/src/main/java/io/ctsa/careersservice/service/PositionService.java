package io.ctsa.careersservice.service;

import io.ctsa.careersservice.model.Position;
import io.ctsa.careersservice.repository.PositionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    public Optional<Position> findById(int id) {
        return positionRepository.findById(id);
    }

    public List<Position> findAll() {
        return positionRepository.findAll();
    }
}
