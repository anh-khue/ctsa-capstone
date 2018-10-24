package io.ctsa.resultssuggestionsservice.model.input;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ctsa.resultssuggestionsservice.model.common.EntranceExamSubject;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "entrance_exam_input_detail", schema = "ctsa_results_suggestions_db")
public class EntranceExamInputDetail {

    private int id;
    private int entranceExamInputId;
    private int entranceExamSubjectId;
    private double mark;
    @JsonIgnore
    private EntranceExamInput entranceExamInput;
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
    @Column(name = "entrance_exam_input_id")
    public int getEntranceExamInputId() {
        return entranceExamInputId;
    }

    public void setEntranceExamInputId(int entranceExamInputId) {
        this.entranceExamInputId = entranceExamInputId;
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
        EntranceExamInputDetail that = (EntranceExamInputDetail) o;
        return id == that.id &&
                entranceExamInputId == that.entranceExamInputId &&
                entranceExamSubjectId == that.entranceExamSubjectId &&
                Double.compare(that.mark, mark) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, entranceExamInputId, entranceExamSubjectId, mark);
    }

    @ManyToOne
    @JoinColumn(name = "entrance_exam_input_id", referencedColumnName = "id", nullable = false,
                insertable = false, updatable = false)
    public EntranceExamInput getEntranceExamInput() {
        return entranceExamInput;
    }

    public void setEntranceExamInput(EntranceExamInput entranceExamInput) {
        this.entranceExamInput = entranceExamInput;
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
