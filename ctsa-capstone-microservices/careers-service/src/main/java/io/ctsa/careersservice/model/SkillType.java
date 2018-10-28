package io.ctsa.careersservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "vietnamese")
    private String vietnamese;
    @Column(name = "business_field_id")
    private int businessFieldId;

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
