package io.ctsa.careersservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
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
    @Column(name = "word")
    private String word;
    @Column(name = "is_synonym")
    private boolean isSynonym;
    @Column(name = "main_keyword_id")
    private Integer mainKeywordId;
    @Column(name = "position_id")
    private Integer positionId;
    @Column(name = "skill_id")
    private Integer skillId;
    @Column(name = "pushed_to_elasticsearch")
    private boolean pushedToElasticsearch;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "main_keyword_id", referencedColumnName = "id",
                updatable = false, insertable = false)
    private Keyword mainKeyword;

    @OneToMany(mappedBy = "mainKeyword")
    private Collection<Keyword> synonyms;

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id",
                updatable = false, insertable = false)
    private Position positionByPositionId;

    @ManyToOne
    @JoinColumn(name = "skill_id", referencedColumnName = "id",
                updatable = false, insertable = false)
    private Skill skillBySkillId;

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
