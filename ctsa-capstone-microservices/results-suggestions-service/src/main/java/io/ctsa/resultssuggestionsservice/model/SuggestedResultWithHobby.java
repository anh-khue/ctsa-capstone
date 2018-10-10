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
public class SuggestedResultWithHobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int suggestedResultId;
    private int hobbyId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuggestedResultWithHobby that = (SuggestedResultWithHobby) o;
        return id == that.id &&
                suggestedResultId == that.suggestedResultId &&
                hobbyId == that.hobbyId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, suggestedResultId, hobbyId);
    }
}
