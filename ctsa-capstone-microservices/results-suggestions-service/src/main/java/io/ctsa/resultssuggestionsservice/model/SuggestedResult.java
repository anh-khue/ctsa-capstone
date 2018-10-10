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
public class SuggestedResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Integer personId;
    private int characteristic;
    private Integer entranceExamResultId;
    private int majorId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuggestedResult that = (SuggestedResult) o;
        return id == that.id &&
                characteristic == that.characteristic &&
                majorId == that.majorId &&
                Objects.equals(personId, that.personId) &&
                Objects.equals(entranceExamResultId, that.entranceExamResultId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personId, characteristic, entranceExamResultId, majorId);
    }
}
