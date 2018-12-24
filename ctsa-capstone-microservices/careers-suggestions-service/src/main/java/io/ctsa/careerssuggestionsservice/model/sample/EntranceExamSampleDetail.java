package io.ctsa.careerssuggestionsservice.model.sample;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ctsa.careerssuggestionsservice.model.common.EntranceExamSubject;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "entrance_exam_sample_detail", schema = "ctsa_career_suggestions_db")
public class EntranceExamSampleDetail {

    private int id;
    private int majorSampleId;
    private int entranceExamSubjectId;
    private double mark;
    @JsonIgnore
    private MajorSample majorSample;
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
    @Column(name = "major_sample_id")
    public int getMajorSampleId() {
        return majorSampleId;
    }

    public void setMajorSampleId(int majorSampleId) {
        this.majorSampleId = majorSampleId;
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
                majorSampleId == that.majorSampleId &&
                entranceExamSubjectId == that.entranceExamSubjectId &&
                Double.compare(that.mark, mark) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, majorSampleId, entranceExamSubjectId, mark);
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
    @JoinColumn(name = "entrance_exam_subject_id", referencedColumnName = "id", nullable = false,
                insertable = false, updatable = false)
    public EntranceExamSubject getEntranceExamSubject() {
        return entranceExamSubject;
    }

    public void setEntranceExamSubject(EntranceExamSubject entranceExamSubject) {
        this.entranceExamSubject = entranceExamSubject;
    }
}
