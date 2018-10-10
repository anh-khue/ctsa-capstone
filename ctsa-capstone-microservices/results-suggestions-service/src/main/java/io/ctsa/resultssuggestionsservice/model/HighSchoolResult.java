package io.ctsa.resultssuggestionsservice.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "high_school_result", schema = "ctsa_results_suggestions_db", catalog = "")
public class HighSchoolResult {

    private int id;
    private int subject;
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

    @Basic
    @Column(name = "subject")
    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
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
        HighSchoolResult that = (HighSchoolResult) o;
        return id == that.id &&
                subject == that.subject &&
                Double.compare(that.mark, mark) == 0 &&
                weight == that.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, mark, weight);
    }
}
