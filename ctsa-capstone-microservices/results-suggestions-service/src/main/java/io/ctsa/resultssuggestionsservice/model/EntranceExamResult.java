package io.ctsa.resultssuggestionsservice.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "entrance_exam_result", schema = "ctsa_results_suggestions_db", catalog = "")
public class EntranceExamResult {

    private int id;
    private double math;
    private double literature;
    private double english;
    private Double physics;
    private Double chemistry;
    private Double biology;
    private Double history;
    private Double geography;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "math")
    public double getMath() {
        return math;
    }

    public void setMath(double math) {
        this.math = math;
    }

    @Basic
    @Column(name = "literature")
    public double getLiterature() {
        return literature;
    }

    public void setLiterature(double literature) {
        this.literature = literature;
    }

    @Basic
    @Column(name = "english")
    public double getEnglish() {
        return english;
    }

    public void setEnglish(double english) {
        this.english = english;
    }

    @Basic
    @Column(name = "physics")
    public Double getPhysics() {
        return physics;
    }

    public void setPhysics(Double physics) {
        this.physics = physics;
    }

    @Basic
    @Column(name = "chemistry")
    public Double getChemistry() {
        return chemistry;
    }

    public void setChemistry(Double chemistry) {
        this.chemistry = chemistry;
    }

    @Basic
    @Column(name = "biology")
    public Double getBiology() {
        return biology;
    }

    public void setBiology(Double biology) {
        this.biology = biology;
    }

    @Basic
    @Column(name = "history")
    public Double getHistory() {
        return history;
    }

    public void setHistory(Double history) {
        this.history = history;
    }

    @Basic
    @Column(name = "geography")
    public Double getGeography() {
        return geography;
    }

    public void setGeography(Double geography) {
        this.geography = geography;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntranceExamResult that = (EntranceExamResult) o;
        return id == that.id &&
                Double.compare(that.math, math) == 0 &&
                Double.compare(that.literature, literature) == 0 &&
                Double.compare(that.english, english) == 0 &&
                Objects.equals(physics, that.physics) &&
                Objects.equals(chemistry, that.chemistry) &&
                Objects.equals(biology, that.biology) &&
                Objects.equals(history, that.history) &&
                Objects.equals(geography, that.geography);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, math, literature, english, physics, chemistry, biology, history, geography);
    }
}
