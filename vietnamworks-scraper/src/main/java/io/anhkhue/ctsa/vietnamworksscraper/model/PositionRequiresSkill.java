package io.anhkhue.ctsa.vietnamworksscraper.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PositionRequiresSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private int positionId;
    @NonNull
    private int skillId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionRequiresSkill that = (PositionRequiresSkill) o;
        return id == that.id &&
                positionId == that.positionId &&
                skillId == that.skillId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, positionId, skillId);
    }
}
