package io.ctsa.careersservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String word;
    private boolean isSynonym;
    private Integer mainKeywordId;
    private Integer positionId;
    private Integer skillId;
    private boolean pushedToElasticsearch;
    @Transient
    private List<String> synonyms;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Keyword keyword = (Keyword) o;
        return id == keyword.id &&
                isSynonym == keyword.isSynonym &&
                pushedToElasticsearch == keyword.pushedToElasticsearch &&
                Objects.equals(word, keyword.word) &&
                Objects.equals(mainKeywordId, keyword.mainKeywordId) &&
                Objects.equals(positionId, keyword.positionId) &&
                Objects.equals(skillId, keyword.skillId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word, isSynonym, mainKeywordId, positionId, skillId, pushedToElasticsearch);
    }
}
