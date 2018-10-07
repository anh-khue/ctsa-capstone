package io.anhkhue.ctsa.myworkscraper.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitmentFact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private Date startDate;
    @NonNull
    private Date endDate;
    @NonNull
    private int positionRequiresSkillId;
    @NonNull
    private int count;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecruitmentFact that = (RecruitmentFact) o;
        return id == that.id &&
                startDate == that.startDate &&
                endDate == that.endDate &&
                positionRequiresSkillId == that.positionRequiresSkillId &&
                count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, positionRequiresSkillId, count);
    }
}
