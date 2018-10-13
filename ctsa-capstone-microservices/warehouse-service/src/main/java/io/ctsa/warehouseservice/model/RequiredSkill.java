package io.ctsa.warehouseservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequiredSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int recruitmentId;
    private int skillId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequiredSkill that = (RequiredSkill) o;
        return id == that.id &&
                recruitmentId == that.recruitmentId &&
                skillId == that.skillId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, recruitmentId, skillId);
    }
}
