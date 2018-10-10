package io.ctsa.resultssuggestionsservice.model;

import lombok.*;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String fullName;
    private int birthYear;
    private int academicTypeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                birthYear == person.birthYear &&
                academicTypeId == person.academicTypeId &&
                Objects.equals(username, person.username) &&
                Objects.equals(password, person.password) &&
                Objects.equals(fullName, person.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, fullName, birthYear, academicTypeId);
    }
}
