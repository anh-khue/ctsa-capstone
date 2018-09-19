package io.ctsa.basedatasetservice.service;

import io.ctsa.basedatasetservice.repository.PositionRepository;
import org.springframework.stereotype.Service;

@Service
public class PositionService {

    private final PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }
}
