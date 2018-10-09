package io.ctsa.careersservice.service;

import io.ctsa.careersservice.model.Keyword;
import io.ctsa.careersservice.repository.KeywordRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KeywordService {

    private final KeywordRepository keywordRepository;

    public KeywordService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    public List<Keyword> getKeywords() {
        List<Keyword> keywords = keywordRepository.findByIsSynonym(false);
        return keywords.stream()
                       .peek(keyword -> keyword.setSynonyms(this.getSynonyms(keyword)))
                       .collect(Collectors.toList());
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

    public Optional<Keyword> findByWord(String word) {
        return keywordRepository.findByWord(word);
    }

    public Optional<Keyword> findById(int id) {
        return keywordRepository.findById(id);
    }

    public List<Keyword> getByPushedToElasticsearch(boolean status) {
        return keywordRepository.findByPushedToElasticsearch(status);
    }

    public void pushedToElasticsearch(String word) {
        this.findByWord(word).ifPresent(keyword -> {
            keyword.setPushedToElasticsearch(true);
            keywordRepository.save(keyword);
        });
    }
}


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class SynonymsWrapper {

    private Keyword keyword;
    private List<String> synonyms;
}
