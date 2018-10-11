package io.ctsa.resultssuggestionsservice.model;

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
public class HighSchoolTopResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int majorResultId;
    private int subjectId;
    private double mark;
    private int weight;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HighSchoolTopResult that = (HighSchoolTopResult) o;
        return id == that.id &&
                majorResultId == that.majorResultId &&
                subjectId == that.subjectId &&
                Double.compare(that.mark, mark) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, majorResultId, subjectId, mark);
    }
}
