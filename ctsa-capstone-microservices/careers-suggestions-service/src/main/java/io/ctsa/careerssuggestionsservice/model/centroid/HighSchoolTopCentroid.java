package io.ctsa.careerssuggestionsservice.model.centroid;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ctsa.careerssuggestionsservice.model.common.HighSchoolSubject;
import io.ctsa.careerssuggestionsservice.model.sample.HighSchoolTopSample;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "high_school_top_centroid", schema = "ctsa_career_suggestions_db")
public class HighSchoolTopCentroid {

    private int id;
    private int majorCentroidId;
    private int subjectId;
    private double mark;
    private double weight;
    @JsonIgnore
    private MajorCentroid majorCentroid;
    private HighSchoolSubject highSchoolSubject;

    public HighSchoolTopCentroid() {
    }

    public HighSchoolTopCentroid(HighSchoolTopSample sample) {
        this.subjectId = sample.getSubjectId();
        this.mark = sample.getMark();
        this.weight = sample.getWeight();
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
    @Column(name = "major_centroid_id")
    public int getMajorCentroidId() {
        return majorCentroidId;
    }

    public void setMajorCentroidId(int majorCentroidId) {
        this.majorCentroidId = majorCentroidId;
    }

    @Basic
    @Column(name = "subject_id")
    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    @Basic
    @Column(name = "mark")
    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    @Basic
    @Column(name = "weight")
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HighSchoolTopCentroid that = (HighSchoolTopCentroid) o;
        return id == that.id &&
                majorCentroidId == that.majorCentroidId &&
                subjectId == that.subjectId &&
                Double.compare(that.mark, mark) == 0 &&
                Double.compare(that.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, majorCentroidId, subjectId, mark, weight);
    }

    @ManyToOne
    @JoinColumn(name = "major_centroid_id", referencedColumnName = "id", nullable = false,
                insertable = false, updatable = false)
    public MajorCentroid getMajorCentroid() {
        return majorCentroid;
    }

    public void setMajorCentroid(MajorCentroid majorCentroid) {
        this.majorCentroid = majorCentroid;
    }

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id", nullable = false,
                insertable = false, updatable = false)
    public HighSchoolSubject getHighSchoolSubject() {
        return highSchoolSubject;
    }

    public void setHighSchoolSubject(HighSchoolSubject highSchoolSubject) {
        this.highSchoolSubject = highSchoolSubject;
    }
}
