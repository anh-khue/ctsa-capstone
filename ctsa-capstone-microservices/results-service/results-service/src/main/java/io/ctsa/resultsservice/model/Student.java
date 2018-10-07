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
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String fullName;
    @NonNull
    private int birthYear;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                birthYear == student.birthYear &&
                Objects.equals(username, student.username) &&
                Objects.equals(password, student.password) &&
                Objects.equals(fullName, student.fullName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, password, fullName, birthYear);
    }
}
