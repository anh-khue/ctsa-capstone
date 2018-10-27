package io.ctsa.resultssuggestionsservice.model.input;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ctsa.resultssuggestionsservice.model.common.HighSchoolSubject;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "high_school_top_input", schema = "ctsa_results_suggestions_db", catalog = "")
public class HighSchoolTopInput {

    private int id;
    private int suggestedMajorInputId;
    private int subjectId;
    private double mark;
    private int weight;
    @JsonIgnore
    private SuggestedMajorInput suggestedMajorInput;
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
    @Column(name = "suggested_major_input_id")
    public int getSuggestedMajorInputId() {
        return suggestedMajorInputId;
    }

    public void setSuggestedMajorInputId(int suggestedMajorInputId) {
        this.suggestedMajorInputId = suggestedMajorInputId;
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
        HighSchoolTopInput topInput = (HighSchoolTopInput) o;
        return id == topInput.id &&
                suggestedMajorInputId == topInput.suggestedMajorInputId &&
                subjectId == topInput.subjectId &&
                Double.compare(topInput.mark, mark) == 0 &&
                weight == topInput.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, suggestedMajorInputId, subjectId, mark, weight);
    }

    @ManyToOne
    @JoinColumn(name = "suggested_major_input_id", referencedColumnName = "id", nullable = false,
                insertable = false, updatable = false)
    public SuggestedMajorInput getSuggestedMajorInput() {
        return suggestedMajorInput;
    }

    public void setSuggestedMajorInput(SuggestedMajorInput suggestedMajorInput) {
        this.suggestedMajorInput = suggestedMajorInput;
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
