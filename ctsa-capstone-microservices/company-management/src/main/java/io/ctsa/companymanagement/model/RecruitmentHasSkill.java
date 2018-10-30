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
    @NonNull
    private int recruitmentId;
    @NonNull
    private int skillId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecruitmentHasSkill that = (RecruitmentHasSkill) o;
        return id == that.id &&
                recruitmentId == that.recruitmentId &&
                skillId == that.skillId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, recruitmentId, skillId);
    }
}
