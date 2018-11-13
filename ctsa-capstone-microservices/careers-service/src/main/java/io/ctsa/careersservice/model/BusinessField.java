package io.ctsa.careersservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "business_field", schema = "ctsa_careers_db")
@NoArgsConstructor
public class BusinessField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "vietnamese")
    private String vietnamese;

    @Column(name = "description")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessField that = (BusinessField) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(vietnamese, that.vietnamese) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, vietnamese, description);
    }
}
