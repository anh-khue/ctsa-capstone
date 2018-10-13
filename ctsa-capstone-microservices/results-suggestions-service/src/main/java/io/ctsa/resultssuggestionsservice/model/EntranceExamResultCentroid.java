package io.ctsa.resultssuggestionsservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@Builder
public class EntranceExamResultCentroid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double math;
    private double literature;
    private double english;
    private Double physics;
    private Double chemistry;
    private Double biology;
    private Double history;
    private Double geography;

    public EntranceExamResultCentroid(EntranceExamResult entranceExamResult) {
        this.math = entranceExamResult.getMath();
        this.literature = entranceExamResult.getLiterature();
        this.english = entranceExamResult.getEnglish();
        this.physics = entranceExamResult.getPhysics();
        this.chemistry = entranceExamResult.getChemistry();
        this.biology = entranceExamResult.getBiology();
        this.history = entranceExamResult.getHistory();
        this.geography = entranceExamResult.getGeography();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMath() {
        return math;
    }

    public void setMath(double math) {
        this.math = math;
    }

    public double getLiterature() {
        return literature;
    }

    public void setLiterature(double literature) {
        this.literature = literature;
    }

    public double getEnglish() {
        return english;
    }

    public void setEnglish(double english) {
        this.english = english;
    }

    public Double getPhysics() {
        return physics;
    }

    public void setPhysics(Double physics) {
        this.physics = physics;
    }

    public Double getChemistry() {
        return chemistry;
    }

    public void setChemistry(Double chemistry) {
        this.chemistry = chemistry;
    }

    public Double getBiology() {
        return biology;
    }

    public void setBiology(Double biology) {
        this.biology = biology;
    }

    public Double getHistory() {
        return history;
    }

    public void setHistory(Double history) {
        this.history = history;
    }

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
        EntranceExamResultCentroid that = (EntranceExamResultCentroid) o;
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
