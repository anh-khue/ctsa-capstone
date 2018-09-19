package io.ctsa.basedatasetservice.service;

import io.ctsa.basedatasetservice.repository.KeywordRepository;
import org.springframework.stereotype.Service;

@Service
public class KeywordService {

    private final KeywordRepository keywordRepository;

    public KeywordService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }
}
