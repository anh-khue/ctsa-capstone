package io.ctsa.careersservice.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "skill_type", schema = "ctsa_careers_db")
public class SkillType {

    private int id;
    private String name;
    private String vietnamese;
    private int businessFieldId;

    public SkillType() {
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

    @Basic
    @Column(name = "vietnamese")
    public String getVietnamese() {
        return vietnamese;
    }

    public void setVietnamese(String vietnamese) {
        this.vietnamese = vietnamese;
    }

    @Basic
    @Column(name = "business_field_id")
    public int getBusinessFieldId() {
        return businessFieldId;
    }

    public void setBusinessFieldId(int businessFieldId) {
        this.businessFieldId = businessFieldId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillType skillType = (SkillType) o;
        return id == skillType.id &&
                businessFieldId == skillType.businessFieldId &&
                Objects.equals(name, skillType.name) &&
                Objects.equals(vietnamese, skillType.vietnamese);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, vietnamese, businessFieldId);
    }
}
