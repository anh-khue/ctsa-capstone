package io.anhkhue.ctsa.myworkscraper.scraper.datacleaner;

import io.anhkhue.ctsa.myworkscraper.communicator.basedataset.BaseDatasetWebClient;
import io.anhkhue.ctsa.myworkscraper.model.Keyword;
import io.anhkhue.ctsa.myworkscraper.model.Position;
import io.anhkhue.ctsa.myworkscraper.model.Skill;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class DataCleaner {

    private final BaseDatasetWebClient baseDatasetWebClient;

    public DataCleaner(BaseDatasetWebClient baseDatasetWebClient) {
        this.baseDatasetWebClient = baseDatasetWebClient;
    }

    public List<Position> extractPositions(List<String> keywords) {
        return cleanSynonyms(retrieveKeywords(keywords)).stream()
                .map(keyword -> {
                    if (keyword.getPositionId() != null) {
                        return this.baseDatasetWebClient.getPositionById(keyword.getPositionId());
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<Skill> extractSkills(List<String> keywords) {
        return cleanSynonyms(retrieveKeywords(keywords)).stream()
                .map(keyword -> {
                    if (keyword.getSkillId() != null) {
                        return this.baseDatasetWebClient.getSkillById(keyword.getSkillId());
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private List<Keyword> retrieveKeywords(List<String> keywords) {
        return keywords.stream()
                .map(this.baseDatasetWebClient::getKeywordByWord)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private List<Keyword> cleanSynonyms(List<Keyword> keywords) {
        return new ArrayList<>(new HashSet<>(keywords.stream()
                .map(keyword -> {
                    if (keyword.isSynonym()) {
                        return this.baseDatasetWebClient.getKeywordById(keyword.getMainKeywordId());
                    }
                    return keyword;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList())));
    }
}