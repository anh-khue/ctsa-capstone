package io.ctsa.careerssuggestionsservice.model.centroid;

import io.ctsa.careerssuggestionsservice.model.common.Major;
import io.ctsa.careerssuggestionsservice.model.sample.MajorSample;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "major_centroid", schema = "ctsa_career_suggestions_db")
public class MajorCentroid {

    private int id;
    private int majorId;
    private double characteristic;
    private double highSchoolAverage;
    private Collection<EntranceExamCentroidDetail> entranceExamCentroidDetails;
    private Collection<HighSchoolTopCentroid> highSchoolTopCentroids;
    private Major major;

    public MajorCentroid() {
    }

    public MajorCentroid(MajorSample sample) {
        this.majorId = sample.getMajorId();
        this.characteristic = sample.getCharacteristic();
        this.highSchoolAverage = sample.getHighSchoolAverage();
    }

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
    @Column(name = "major_id")
    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
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
    @Column(name = "high_school_average")
    public double getHighSchoolAverage() {
        return highSchoolAverage;
    }

    public void setHighSchoolAverage(double highSchoolAverage) {
        this.highSchoolAverage = highSchoolAverage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MajorCentroid that = (MajorCentroid) o;
        return id == that.id &&
                majorId == that.majorId &&
                Double.compare(that.characteristic, characteristic) == 0 &&
                Double.compare(that.highSchoolAverage, highSchoolAverage) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, majorId, characteristic, highSchoolAverage);
    }

    @OneToMany(mappedBy = "majorCentroid")
    @LazyCollection(LazyCollectionOption.FALSE)
    public Collection<EntranceExamCentroidDetail> getEntranceExamCentroidDetails() {
        return entranceExamCentroidDetails;
    }

    public void setEntranceExamCentroidDetails(Collection<EntranceExamCentroidDetail> entranceExamCentroidDetails) {
        this.entranceExamCentroidDetails = entranceExamCentroidDetails;
    }

    @OneToMany(mappedBy = "majorCentroid")
    @LazyCollection(LazyCollectionOption.FALSE)
    public Collection<HighSchoolTopCentroid> getHighSchoolTopCentroids() {
        return highSchoolTopCentroids;
    }

    public void setHighSchoolTopCentroids(Collection<HighSchoolTopCentroid> highSchoolTopCentroids) {
        this.highSchoolTopCentroids = highSchoolTopCentroids;
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
