package io.ctsa.resultssuggestionsservice.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class SuggestedMajor {

    private int id;
    private int characteristic;
    private Double highSchoolAverage;

    @Transient
    private EntranceExamInput entranceExamInput;

    @Transient
    private List<HighSchoolTopInput> highSchoolTopInputs;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "characteristic")
    public int getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(int characteristic) {
        this.characteristic = characteristic;
    }

    @Basic
    @Column(name = "high_school_average")
    public Double getHighSchoolAverage() {
        return highSchoolAverage;
    }

    public void setHighSchoolAverage(Double highSchoolAverage) {
        this.highSchoolAverage = highSchoolAverage;
    }

    @Transient
    public List<HighSchoolTopInput> getHighSchoolTopInputs() {
        return highSchoolTopInputs;
    }

    @Transient
    public void setHighSchoolTopInputs(List<HighSchoolTopInput> highSchoolTopInputs) {
        this.highSchoolTopInputs = highSchoolTopInputs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuggestedMajor that = (SuggestedMajor) o;
        return id == that.id &&
                characteristic == that.characteristic &&
                Objects.equals(highSchoolAverage, that.highSchoolAverage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, characteristic, highSchoolAverage);
    }

    @Transient
    public EntranceExamInput getEntranceExamInput() {
        return entranceExamInput;
    }

    @Transient
    public void setEntranceExamInput(EntranceExamInput entranceExamInput) {
        this.entranceExamInput = entranceExamInput;
    }
}
