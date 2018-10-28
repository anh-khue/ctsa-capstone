package io.ctsa.careersservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
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
    private Position positionByPositionId;
    private Skill skillBySkillId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "word")
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Basic
    @Column(name = "is_synonym")
    public boolean isSynonym() {
        return isSynonym;
    }

    public void setSynonym(boolean synonym) {
        isSynonym = synonym;
    }

    @Basic
    @Column(name = "main_keyword_id")
    public Integer getMainKeywordId() {
        return mainKeywordId;
    }

    public void setMainKeywordId(Integer mainKeywordId) {
        this.mainKeywordId = mainKeywordId;
    }

    @Basic
    @Column(name = "position_id")
    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    @Basic
    @Column(name = "skill_id")
    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    @Basic
    @Column(name = "pushed_to_elasticsearch")
    public boolean isPushedToElasticsearch() {
        return pushedToElasticsearch;
    }

    public void setPushedToElasticsearch(boolean pushedToElasticsearch) {
        this.pushedToElasticsearch = pushedToElasticsearch;
    }

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

    @ManyToOne
    @JoinColumn(name = "main_keyword_id", referencedColumnName = "id",
                updatable = false, insertable = false)
    public Keyword getMainKeyword() {
        return mainKeyword;
    }

    public void setMainKeyword(Keyword mainKeyword) {
        this.mainKeyword = mainKeyword;
    }

    @OneToMany(mappedBy = "mainKeyword")
    public Collection<Keyword> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(Collection<Keyword> synonyms) {
        this.synonyms = synonyms;
    }

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id",
                updatable = false, insertable = false)
    public Position getPositionByPositionId() {
        return positionByPositionId;
    }

    public void setPositionByPositionId(Position positionByPositionId) {
        this.positionByPositionId = positionByPositionId;
    }

    @ManyToOne
    @JoinColumn(name = "skill_id", referencedColumnName = "id",
                updatable = false, insertable = false)
    public Skill getSkillBySkillId() {
        return skillBySkillId;
    }

    public void setSkillBySkillId(Skill skillBySkillId) {
        this.skillBySkillId = skillBySkillId;
    }
}
