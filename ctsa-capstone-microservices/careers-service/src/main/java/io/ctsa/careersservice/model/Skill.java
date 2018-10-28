package io.ctsa.careersservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Skill {

    @Id
    private int id;
    private String name;
    private String imageUrl;
    private int skillTypeId;
    private SkillType skillType;

    public Skill() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return id == skill.id &&
                Objects.equals(name, skill.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Basic
    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Basic
    @Column(name = "skill_type_id")
    public int getSkillTypeId() {
        return skillTypeId;
    }

    public void setSkillTypeId(int skillTypeId) {
        this.skillTypeId = skillTypeId;
    }

    @ManyToOne
    @JoinColumn(name = "skill_type_id", referencedColumnName = "id", nullable = false,
                insertable = false, updatable = false)
    public SkillType getSkillType() {
        return skillType;
    }

    public void setSkillType(SkillType skillType) {
        this.skillType = skillType;
    }
}
