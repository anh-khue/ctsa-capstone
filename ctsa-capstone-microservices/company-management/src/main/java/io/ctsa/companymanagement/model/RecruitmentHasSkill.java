package io.ctsa.companymanagement.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitmentHasSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int recruitmentId;
    private int skillId;
    private String skillName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecruitmentHasSkill that = (RecruitmentHasSkill) o;
        return id == that.id &&
                recruitmentId == that.recruitmentId &&
                skillId == that.skillId &&
                Objects.equals(skillName, that.skillName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, recruitmentId, skillId, skillName);
    }
}
