package io.ctsa.careerssuggestionsservice.model.centroid;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ctsa.careerssuggestionsservice.model.common.EntranceExamSubject;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "entrance_exam_centroid_detail", schema = "ctsa_career_suggestions_db")
public class EntranceExamCentroidDetail {

    private int id;
    private int majorCentroidId;
    private int entranceExamSubjectId;
    private double mark;
    private int weight;
    @JsonIgnore
    private MajorCentroid majorCentroid;
    private EntranceExamSubject entranceExamSubject;

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
    @Column(name = "entrance_exam_subject_id")
    public int getEntranceExamSubjectId() {
        return entranceExamSubjectId;
    }

    public void setEntranceExamSubjectId(int entranceExamSubjectId) {
        this.entranceExamSubjectId = entranceExamSubjectId;
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
        EntranceExamCentroidDetail that = (EntranceExamCentroidDetail) o;
        return id == that.id &&
                majorCentroidId == that.majorCentroidId &&
                entranceExamSubjectId == that.entranceExamSubjectId &&
                Double.compare(that.mark, mark) == 0 &&
                weight == that.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, majorCentroidId, entranceExamSubjectId, mark, weight);
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
    @JoinColumn(name = "entrance_exam_subject_id", referencedColumnName = "id", nullable = false,
                insertable = false, updatable = false)
    public EntranceExamSubject getEntranceExamSubject() {
        return entranceExamSubject;
    }

    public void setEntranceExamSubject(EntranceExamSubject entranceExamSubject) {
        this.entranceExamSubject = entranceExamSubject;
    }
}
