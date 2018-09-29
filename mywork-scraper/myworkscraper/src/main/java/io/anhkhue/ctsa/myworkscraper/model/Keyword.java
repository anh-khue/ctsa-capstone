package io.anhkhue.ctsa.myworkscraper.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NonNull
    private boolean isSynonym;
    private Integer mainKeywordId;
    private Integer positionId;
    private Integer skillId;
    @NonNull
    private boolean pushedToElasticsearch;

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
