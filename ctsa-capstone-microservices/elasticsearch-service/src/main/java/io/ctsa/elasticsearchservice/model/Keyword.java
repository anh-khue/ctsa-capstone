package io.ctsa.elasticsearchservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Keyword {

    private int id;
    private String word;
    private boolean isSynonym;
    private Integer mainKeywordId;
    private Integer positionId;
    private Integer skillId;
    private boolean pushedToElasticsearch;
    @JsonIgnore
    private Keyword mainKeyword;
    private Collection<Keyword> synonyms;
//    private Position positionByPositionId;
//    private Skill skillBySkillId;
}
