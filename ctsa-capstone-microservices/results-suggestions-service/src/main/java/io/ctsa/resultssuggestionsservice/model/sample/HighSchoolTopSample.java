package io.ctsa.resultssuggestionsservice.model.sample;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ctsa.resultssuggestionsservice.model.common.HighSchoolSubject;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "high_school_top_sample", schema = "ctsa_results_suggestions_db", catalog = "")
public class HighSchoolTopSample {

    private int id;
    private int majorSampleId;
    private int subjectId;
    private double mark;
    private int weight;
    @JsonIgnore
    private MajorSample majorSample;
    private HighSchoolSubject highSchoolSubject;

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
    @Column(name = "major_sample_id")
    public int getMajorSampleId() {
        return majorSampleId;
    }

    public void setMajorSampleId(int majorSampleId) {
        this.majorSampleId = majorSampleId;
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
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HighSchoolTopSample that = (HighSchoolTopSample) o;
        return id == that.id &&
                majorSampleId == that.majorSampleId &&
                subjectId == that.subjectId &&
                Double.compare(that.mark, mark) == 0 &&
                weight == that.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, majorSampleId, subjectId, mark, weight);
    }

    @ManyToOne
    @JoinColumn(name = "major_sample_id", referencedColumnName = "id", nullable = false,
                insertable = false, updatable = false)
    public MajorSample getMajorSample() {
        return majorSample;
    }

    public void setMajorSample(MajorSample majorSample) {
        this.majorSample = majorSample;
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
