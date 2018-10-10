package io.anhkhue.ctsa.topitworksscraper.scraper.datacleaner;

import io.anhkhue.ctsa.topitworksscraper.communicator.careersservice.CareersServiceWebClient;
import io.anhkhue.ctsa.topitworksscraper.model.Keyword;
import io.anhkhue.ctsa.topitworksscraper.model.Position;
import io.anhkhue.ctsa.topitworksscraper.model.Skill;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class DataCleaner {

    private final CareersServiceWebClient careersServiceWebClient;

    public DataCleaner(CareersServiceWebClient careersServiceWebClient) {
        this.careersServiceWebClient = careersServiceWebClient;
    }

    public List<Position> extractPositions(List<String> keywords) {
        return cleanSynonyms(retrieveKeywords(keywords)).stream()
                                                        .map(keyword -> {
                                                            if (keyword.getPositionId() != null) {
                                                                return this.careersServiceWebClient.getPositionById(keyword.getPositionId());
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
                                                                return this.careersServiceWebClient.getSkillById(keyword.getSkillId());
                                                            } else {
                                                                return null;
                                                            }
                                                        })
                                                        .filter(Objects::nonNull)
                                                        .collect(Collectors.toList());
    }

    private List<Keyword> retrieveKeywords(List<String> keywords) {
        return keywords.stream()
                       .map(this.careersServiceWebClient::getKeywordByWord)
                       .filter(Objects::nonNull)
                       .collect(Collectors.toList());
    }

    private List<Keyword> cleanSynonyms(List<Keyword> keywords) {
        return new ArrayList<>(new HashSet<>(keywords.stream()
                                                     .map(keyword -> {
                                                         if (keyword.isSynonym()) {
                                                             return this.careersServiceWebClient.getKeywordById(keyword.getMainKeywordId());
                                                         }
                                                         return keyword;
                                                     })
                                                     .filter(Objects::nonNull)
                                                     .collect(Collectors.toList())));
    }
}