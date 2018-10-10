package io.ctsa.resultssuggestionsservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String name;
    private int weight;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hobby hobby = (Hobby) o;
        return id == hobby.id &&
                weight == hobby.weight &&
                Objects.equals(name, hobby.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, weight);
    }
}
