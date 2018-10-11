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
public class EntranceExamResultCentroid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double math;
    private double literature;
    private double english;
    private Double physics;
    private Double chemistry;
    private Double biology;
    private Double history;
    private Double geography;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntranceExamResultCentroid that = (EntranceExamResultCentroid) o;
        return id == that.id &&
                Double.compare(that.math, math) == 0 &&
                Double.compare(that.literature, literature) == 0 &&
                Double.compare(that.english, english) == 0 &&
                Objects.equals(physics, that.physics) &&
                Objects.equals(chemistry, that.chemistry) &&
                Objects.equals(biology, that.biology) &&
                Objects.equals(history, that.history) &&
                Objects.equals(geography, that.geography);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, math, literature, english, physics, chemistry, biology, history, geography);
    }
}
