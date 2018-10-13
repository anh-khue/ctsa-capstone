package io.ctsa.resultssuggestionsservice.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class HighSchoolTopInput {

    private int id;
    private int suggestedMajorId;
    private int subjectId;
    private double mark;
    private int weight;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSuggestedMajorId() {
        return suggestedMajorId;
    }

    public void setSuggestedMajorId(int suggestedMajorId) {
        this.suggestedMajorId = suggestedMajorId;
    }

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
        HighSchoolTopInput that = (HighSchoolTopInput) o;
        return id == that.id &&
                Double.compare(that.mark, mark) == 0 &&
                weight == that.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mark, weight);
    }
}
