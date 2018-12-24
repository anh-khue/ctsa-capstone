package io.ctsa.careerssuggestionsservice.model.sample;

import io.ctsa.careerssuggestionsservice.model.common.Major;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "major_sample", schema = "ctsa_career_suggestions_db")
public class MajorSample {

    private int id;
    private double characteristic;
    private int majorId;
    private Double highSchoolAverage;
    private Collection<EntranceExamSampleDetail> entranceExamSampleDetails;
    private Collection<HighSchoolTopSample> highSchoolTopSamples;
    private Major majorByMajorId;

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
    @Column(name = "characteristic")
    public double getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(double characteristic) {
        this.characteristic = characteristic;
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
                Objects.equals(highSchoolAverage, that.highSchoolAverage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, characteristic, majorId, highSchoolAverage);
    }

    @OneToMany(mappedBy = "majorSample")
    public Collection<EntranceExamSampleDetail> getEntranceExamSampleDetails() {
        return entranceExamSampleDetails;
    }

    public void setEntranceExamSampleDetails(Collection<EntranceExamSampleDetail> entranceExamSampleDetails) {
        this.entranceExamSampleDetails = entranceExamSampleDetails;
    }

    @OneToMany(mappedBy = "majorSample")
    public Collection<HighSchoolTopSample> getHighSchoolTopSamples() {
        return highSchoolTopSamples;
    }

    public void setHighSchoolTopSamples(Collection<HighSchoolTopSample> highSchoolTopSamples) {
        this.highSchoolTopSamples = highSchoolTopSamples;
    }

    @ManyToOne
    @JoinColumn(name = "major_id", referencedColumnName = "id", nullable = false,
                insertable = false, updatable = false)
    public Major getMajorByMajorId() {
        return majorByMajorId;
    }

    public void setMajorByMajorId(Major majorByMajorId) {
        this.majorByMajorId = majorByMajorId;
    }
}
