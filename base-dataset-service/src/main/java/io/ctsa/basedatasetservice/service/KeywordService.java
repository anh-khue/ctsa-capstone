package io.ctsa.basedatasetservice.service;

import io.ctsa.basedatasetservice.model.Keyword;
import io.ctsa.basedatasetservice.repository.KeywordRepository;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class KeywordService {

    private final KeywordRepository keywordRepository;

    public KeywordService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    public Map<Keyword, List<String>> getKeywordsMap() {
        List<Keyword> keywords = keywordRepository.findByIsSynonym(false);
        return keywords.stream()
                       .collect(Collectors.toMap(keyword -> keyword,
                                                 this::getSynonyms));
    }

    private List<String> getSynonyms(Keyword keyword) {
        return keywordRepository.findByMainKeywordId(keyword.getId())
                                .stream()
                                .map(Keyword::getWord)
                                .collect(Collectors.toList());
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
}
