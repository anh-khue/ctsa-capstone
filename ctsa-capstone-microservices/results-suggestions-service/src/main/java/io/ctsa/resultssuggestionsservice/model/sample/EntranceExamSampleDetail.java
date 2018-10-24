package io.ctsa.resultssuggestionsservice.model.sample;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ctsa.resultssuggestionsservice.model.common.EntranceExamSubject;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "entrance_exam_sample_detail", schema = "ctsa_results_suggestions_db", catalog = "")
public class EntranceExamSampleDetail {

    private int id;
    private int entranceExamSampleId;
    private int entranceExamSubjectId;
    private double mark;
    @JsonIgnore
    private EntranceExamSample entranceExamSample;
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
    @Column(name = "entrance_exam_sample_id")
    public int getEntranceExamSampleId() {
        return entranceExamSampleId;
    }

    public void setEntranceExamSampleId(int entranceExamSampleId) {
        this.entranceExamSampleId = entranceExamSampleId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntranceExamSampleDetail that = (EntranceExamSampleDetail) o;
        return id == that.id &&
                entranceExamSampleId == that.entranceExamSampleId &&
                entranceExamSubjectId == that.entranceExamSubjectId &&
                Double.compare(that.mark, mark) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, entranceExamSampleId, entranceExamSubjectId, mark);
    }

    @ManyToOne
    @JoinColumn(name = "entrance_exam_sample_id", referencedColumnName = "id", nullable = false,
                insertable = false, updatable = false)
    public EntranceExamSample getEntranceExamSample() {
        return entranceExamSample;
    }

    public void setEntranceExamSample(EntranceExamSample entranceExamSample) {
        this.entranceExamSample = entranceExamSample;
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
