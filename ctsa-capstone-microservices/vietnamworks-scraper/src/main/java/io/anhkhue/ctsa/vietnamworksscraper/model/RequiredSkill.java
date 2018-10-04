package io.anhkhue.ctsa.vietnamworksscraper.model;

import lombok.*;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequiredSkill {

    private int id;
    @NonNull
    private int recruitmentId;
    @NonNull
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
