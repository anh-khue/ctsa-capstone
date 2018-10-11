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
public class MajorResultWithHobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int majorResultId;
    private int hobbyId;
    private int weight;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MajorResultWithHobby that = (MajorResultWithHobby) o;
        return id == that.id &&
                majorResultId == that.majorResultId &&
                hobbyId == that.hobbyId &&
                weight == that.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, majorResultId, hobbyId, weight);
    }
}
