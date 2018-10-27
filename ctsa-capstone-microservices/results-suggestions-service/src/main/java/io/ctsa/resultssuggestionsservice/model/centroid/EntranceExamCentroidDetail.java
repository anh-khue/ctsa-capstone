package io.ctsa.resultssuggestionsservice.model.centroid;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ctsa.resultssuggestionsservice.model.common.EntranceExamSubject;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "entrance_exam_centroid_detail", schema = "ctsa_results_suggestions_db", catalog = "")
public class EntranceExamCentroidDetail {

    private int id;
    private int entranceExamCentroidId;
    private int entranceExamSubjectId;
    private double mark;
    private int weight;
    @JsonIgnore
    private EntranceExamCentroid entranceExamCentroid;
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
    @Column(name = "entrance_exam_centroid_id")
    public int getEntranceExamCentroidId() {
        return entranceExamCentroidId;
    }

    public void setEntranceExamCentroidId(int entranceExamCentroidId) {
        this.entranceExamCentroidId = entranceExamCentroidId;
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
                entranceExamCentroidId == that.entranceExamCentroidId &&
                entranceExamSubjectId == that.entranceExamSubjectId &&
                Double.compare(that.mark, mark) == 0 &&
                weight == that.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, entranceExamCentroidId, entranceExamSubjectId, mark, weight);
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
