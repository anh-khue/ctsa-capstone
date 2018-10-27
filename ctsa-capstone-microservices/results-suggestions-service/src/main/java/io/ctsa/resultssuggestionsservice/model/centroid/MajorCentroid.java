package io.ctsa.resultssuggestionsservice.model.centroid;

import io.ctsa.resultssuggestionsservice.model.common.Major;
import io.ctsa.resultssuggestionsservice.model.sample.MajorSample;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "major_centroid", schema = "ctsa_results_suggestions_db", catalog = "")
public class MajorCentroid {

    private int id;
    private int majorId;
    private double characteristic;
    private int entranceExamCentroidId;
    private double highSchoolAverage;
    private Collection<HighSchoolTopCentroid> highSchoolTopCentroids;
    private Major major;
    private EntranceExamCentroid entranceExamCentroid;

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
    @Column(name = "entrance_exam_centroid_id")
    public int getEntranceExamCentroidId() {
        return entranceExamCentroidId;
    }

    public void setEntranceExamCentroidId(int entranceExamCentroidId) {
        this.entranceExamCentroidId = entranceExamCentroidId;
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
                entranceExamCentroidId == that.entranceExamCentroidId &&
                Double.compare(that.highSchoolAverage, highSchoolAverage) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, majorId, characteristic, entranceExamCentroidId, highSchoolAverage);
    }

    @OneToMany(mappedBy = "majorCentroid")
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

    @ManyToOne
    @JoinColumn(name = "entrance_exam_centroid_id", referencedColumnName = "id", nullable = false,
                insertable = false, updatable = false)
    public EntranceExamCentroid getEntranceExamCentroid() {
        return entranceExamCentroid;
    }

    public void setEntranceExamCentroid(EntranceExamCentroid entranceExamCentroid) {
        this.entranceExamCentroid = entranceExamCentroid;
    }
}
