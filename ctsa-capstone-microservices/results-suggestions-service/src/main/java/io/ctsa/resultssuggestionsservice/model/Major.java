package io.ctsa.resultssuggestionsservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Major {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String name;
    private int businessFieldId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Major major = (Major) o;
        return id == major.id &&
                businessFieldId == major.businessFieldId &&
                Objects.equals(name, major.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, businessFieldId);
    }
}
