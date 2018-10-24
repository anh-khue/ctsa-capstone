package io.ctsa.resultssuggestionsservice.model.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ctsa.resultssuggestionsservice.model.input.SuggestedMajorInput;
import io.ctsa.resultssuggestionsservice.model.sample.MajorSample;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Person {

    private int id;
    private String username;
    private String password;
    private String fullName;
    private int birthYear;
    private int academicTypeId;
    @JsonIgnore
    private Collection<MajorSample> majorSamples;
    private AcademicType academicType;
    @JsonIgnore
    private Collection<SuggestedMajorInput> suggestedMajorInputs;
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
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "full_name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "birth_year")
    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    @Basic
    @Column(name = "academic_type_id")
    public int getAcademicTypeId() {
        return academicTypeId;
    }

    public void setAcademicTypeId(int academicTypeId) {
        this.academicTypeId = academicTypeId;
    }

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

    @OneToMany(mappedBy = "person")
    public Collection<MajorSample> getMajorSamples() {
        return majorSamples;
    }

    public void setMajorSamples(Collection<MajorSample> majorSamples) {
        this.majorSamples = majorSamples;
    }

    @ManyToOne
    @JoinColumn(name = "academic_type_id", referencedColumnName = "id", nullable = false,
                insertable = false, updatable = false)
    public AcademicType getAcademicType() {
        return academicType;
    }

    public void setAcademicType(AcademicType academicType) {
        this.academicType = academicType;
    }

    @OneToMany(mappedBy = "person")
    public Collection<SuggestedMajorInput> getSuggestedMajorInputs() {
        return suggestedMajorInputs;
    }

    public void setSuggestedMajorInputs(Collection<SuggestedMajorInput> suggestedMajorInputs) {
        this.suggestedMajorInputs = suggestedMajorInputs;
    }
}
