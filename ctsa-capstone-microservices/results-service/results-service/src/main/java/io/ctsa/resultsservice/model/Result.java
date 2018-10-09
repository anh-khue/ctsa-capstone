package io.ctsa.resultsservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Integer studentId;
    @NonNull
    private int characteristic;
    @NonNull
    private double math;
    @NonNull
    private double literature;
    @NonNull
    private double english;
    private Double physics;
    private Double chemistry;
    private Double biology;
    private Double history;
    private Double geography;
    private Double ethics;
    @NonNull
    private int majorId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return id == result.id &&
                characteristic == result.characteristic &&
                Double.compare(result.math, math) == 0 &&
                Double.compare(result.literature, literature) == 0 &&
                Double.compare(result.english, english) == 0 &&
                majorId == result.majorId &&
                Objects.equals(studentId, result.studentId) &&
                Objects.equals(physics, result.physics) &&
                Objects.equals(chemistry, result.chemistry) &&
                Objects.equals(biology, result.biology) &&
                Objects.equals(history, result.history) &&
                Objects.equals(geography, result.geography) &&
                Objects.equals(ethics, result.ethics);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, studentId, characteristic, math, literature, english, physics, chemistry, biology, history, geography, ethics, majorId);
    }
}
