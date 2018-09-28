package io.anhkhue.ctsa.vietnamworksscraper.model;

import lombok.*;

import javax.persistence.*;
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
    private int day;
    @NonNull
    private int month;
    @NonNull
    private int year;
    @NonNull
    private int positionRequiresSkillId;
    @NonNull
    private int count;
    @NonNull
    private int totalPosition;
    @NonNull
    private int shortestDuration;
    @NonNull
    private int longestDuration;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecruitmentFact that = (RecruitmentFact) o;
        return id == that.id &&
                day == that.day &&
                month == that.month &&
                year == that.year &&
                positionRequiresSkillId == that.positionRequiresSkillId &&
                count == that.count &&
                totalPosition == that.totalPosition &&
                shortestDuration == that.shortestDuration &&
                longestDuration == that.longestDuration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, day, month, year, positionRequiresSkillId, count, totalPosition, shortestDuration, longestDuration);
    }
}
