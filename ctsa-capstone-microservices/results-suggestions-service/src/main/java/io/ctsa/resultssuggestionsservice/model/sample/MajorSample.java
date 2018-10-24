package io.ctsa.resultssuggestionsservice.model.sample;

import io.ctsa.resultssuggestionsservice.model.common.Major;
import io.ctsa.resultssuggestionsservice.model.common.Person;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "major_sample", schema = "ctsa_results_suggestions_db", catalog = "")
public class MajorSample {

    private int id;
    private Integer personId;
    private double characteristic;
    private Integer entranceExamSampleId;
    private int majorId;
    private Double highSchoolAverage;
    private Collection<HighSchoolTopSample> highSchoolTopSamples;
    private Person person;
    private EntranceExamSample entranceExamSample;
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
    public double getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(double characteristic) {
        this.characteristic = characteristic;
    }

    @Basic
    @Column(name = "entrance_exam_sample_id")
    public Integer getEntranceExamSampleId() {
        return entranceExamSampleId;
    }

    public void setEntranceExamSampleId(Integer entranceExamSampleId) {
        this.entranceExamSampleId = entranceExamSampleId;
    }

    @Basic
    @Column(name = "major_id")
    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
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
        MajorSample that = (MajorSample) o;
        return id == that.id &&
                Double.compare(that.characteristic, characteristic) == 0 &&
                majorId == that.majorId &&
                Objects.equals(personId, that.personId) &&
                Objects.equals(entranceExamSampleId, that.entranceExamSampleId) &&
                Objects.equals(highSchoolAverage, that.highSchoolAverage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personId, characteristic, entranceExamSampleId, majorId, highSchoolAverage);
    }

    @OneToMany(mappedBy = "majorSample")
    public Collection<HighSchoolTopSample> getHighSchoolTopSamples() {
        return highSchoolTopSamples;
    }

    public void setHighSchoolTopSamples(Collection<HighSchoolTopSample> highSchoolTopSamples) {
        this.highSchoolTopSamples = highSchoolTopSamples;
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
    @JoinColumn(name = "entrance_exam_sample_id", referencedColumnName = "id",
                insertable = false, updatable = false)
    public EntranceExamSample getEntranceExamSample() {
        return entranceExamSample;
    }

    public void setEntranceExamSample(EntranceExamSample entranceExamSample) {
        this.entranceExamSample = entranceExamSample;
    }

    @ManyToOne
    @JoinColumn(name = "major_id", referencedColumnName = "id", nullable = false,
                insertable = false, updatable = false)
    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }
}
