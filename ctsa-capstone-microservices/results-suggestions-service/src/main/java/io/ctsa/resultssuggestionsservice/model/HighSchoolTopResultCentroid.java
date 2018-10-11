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
public class HighSchoolTopResultCentroid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int majorCentroidId;
    private int subjectId;
    private double mark;
    private double weight;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HighSchoolTopResultCentroid that = (HighSchoolTopResultCentroid) o;
        return id == that.id &&
                majorCentroidId == that.majorCentroidId &&
                subjectId == that.subjectId &&
                Double.compare(that.mark, mark) == 0 &&
                Double.compare(that.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, majorCentroidId, subjectId, mark, weight);
    }
}
