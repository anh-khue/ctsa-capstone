package io.ctsa.resultssuggestionsservice.model.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "academic_type", schema = "ctsa_results_suggestions_db", catalog = "")
public class AcademicType {

    private int id;
    private String name;
    @JsonIgnore
    private Collection<Person> people;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicType that = (AcademicType) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "academicType")
    public Collection<Person> getPeople() {
        return people;
    }

    public void setPeople(Collection<Person> people) {
        this.people = people;
    }
}
