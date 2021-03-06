package io.ctsa.careersservice.service;

import io.ctsa.careersservice.model.Keyword;
import io.ctsa.careersservice.repository.KeywordRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KeywordService {

    private final KeywordRepository keywordRepository;

    public KeywordService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    public List<Keyword> getKeywords() {
        return keywordRepository.findByIsSynonym(false);
    }

    public Page<Keyword> getKeywordsByPage(int itemPerPage, int page) {
        Pageable pageRequest = PageRequest.of(page - 1, itemPerPage);
        return keywordRepository.findByIsSynonym(false, pageRequest);
    }

    public void insertSynonyms(List<String> words, Integer mainKeywordId)
            throws Exception {
        for (String word : words) {
            insertKeyword(word, mainKeywordId, null, null);
        }
    }

    void insertKeyword(String word, Integer mainKeywordId,
                       Integer positionId, Integer skillId)
            throws Exception {
        try {
            boolean isSynonym = mainKeywordId != null;
            Keyword keyword = Keyword.builder()
                                     .word(word)
                                     .isSynonym(isSynonym)
                                     .mainKeywordId(mainKeywordId)
                                     .positionId(positionId)
                                     .skillId(skillId)
                                     .pushedToElasticsearch(false)
                                     .build();
            keywordRepository.save(keyword);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public Optional<Keyword> findByWord(String word) {
        return keywordRepository.findByWord(word);
    }

    public Optional<Keyword> findById(int id) {
        return keywordRepository.findById(id);
    }

    public List<Keyword> getByPushedToElasticsearch(boolean status) {
        return keywordRepository.findByPushedToElasticsearch(status);
    }

    public void pushToElasticsearch(String word) {
        this.findByWord(word).ifPresent(keyword -> {
            keyword.setPushedToElasticsearch(true);
            keywordRepository.save(keyword);
        });
    }
}