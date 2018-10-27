package io.ctsa.resultssuggestionsservice.model.input;

import io.ctsa.resultssuggestionsservice.model.common.Major;
import io.ctsa.resultssuggestionsservice.model.common.Person;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "suggested_major_input", schema = "ctsa_results_suggestions_db")
public class SuggestedMajorInput {

    private int id;
    private Integer personId;
    private int characteristic;
    private Integer entranceExamInputId;
    private Integer majorId;
    private Double highSchoolAverage;
    private Collection<HighSchoolTopInput> highSchoolTopInputs;
    private Person person;
    private EntranceExamInput entranceExamInput;
    private Major major;

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
    @Column(name = "person_id")
    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "characteristic")
    public int getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(int characteristic) {
        this.characteristic = characteristic;
    }

    @Basic
    @Column(name = "entrance_exam_input_id")
    public Integer getEntranceExamInputId() {
        return entranceExamInputId;
    }

    public void setEntranceExamInputId(Integer entranceExamInputId) {
        this.entranceExamInputId = entranceExamInputId;
    }

    @Basic
    @Column(name = "major_id")
    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    @Basic
    @Column(name = "high_school_average")
    public Double getHighSchoolAverage() {
        return highSchoolAverage;
    }

    public void setHighSchoolAverage(Double highSchoolAverage) {
        this.highSchoolAverage = highSchoolAverage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuggestedMajorInput that = (SuggestedMajorInput) o;
        return id == that.id &&
                characteristic == that.characteristic &&
                Objects.equals(personId, that.personId) &&
                Objects.equals(entranceExamInputId, that.entranceExamInputId) &&
                Objects.equals(majorId, that.majorId) &&
                Objects.equals(highSchoolAverage, that.highSchoolAverage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personId, characteristic, entranceExamInputId, majorId, highSchoolAverage);
    }

    @OneToMany(mappedBy = "suggestedMajorInput")
    public Collection<HighSchoolTopInput> getHighSchoolTopInputs() {
        return highSchoolTopInputs;
    }

    public void setHighSchoolTopInputs(Collection<HighSchoolTopInput> highSchoolTopInputs) {
        this.highSchoolTopInputs = highSchoolTopInputs;
    }

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id",
                insertable = false, updatable = false)
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @ManyToOne
    @JoinColumn(name = "entrance_exam_input_id", referencedColumnName = "id",
                insertable = false, updatable = false)
    public EntranceExamInput getEntranceExamInput() {
        return entranceExamInput;
    }

    public void setEntranceExamInput(EntranceExamInput entranceExamInput) {
        this.entranceExamInput = entranceExamInput;
    }

    @ManyToOne
    @JoinColumn(name = "major_id", referencedColumnName = "id",
                insertable = false, updatable = false)
    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }
}
