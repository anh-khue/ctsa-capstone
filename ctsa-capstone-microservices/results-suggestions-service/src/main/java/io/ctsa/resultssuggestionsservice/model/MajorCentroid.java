package io.ctsa.resultssuggestionsservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MajorCentroid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int majorId;
    private int characteristic;
    private int entranceExamResultCentroidId;

    // Object Relational Mapping
    @Transient
    private Major major;
    @Transient
    private EntranceExamResultCentroid entranceExamResultCentroid;
    @Transient
    private List<HighSchoolTopResultCentroid> highSchoolTopResultCentroids;
    @Transient
    private List<MajorCentroidWithHobby> majorCentroidWithHobbies;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public int getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(int characteristic) {
        this.characteristic = characteristic;
    }

    public int getEntranceExamResultCentroidId() {
        return entranceExamResultCentroidId;
    }

    public void setEntranceExamResultCentroidId(int entranceExamResultCentroidId) {
        this.entranceExamResultCentroidId = entranceExamResultCentroidId;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public EntranceExamResultCentroid getEntranceExamResultCentroid() {
        return entranceExamResultCentroid;
    }

    public void setEntranceExamResultCentroid(EntranceExamResultCentroid entranceExamResultCentroid) {
        this.entranceExamResultCentroid = entranceExamResultCentroid;
    }

    public List<HighSchoolTopResultCentroid> getHighSchoolTopResultCentroids() {
        return highSchoolTopResultCentroids;
    }

    public void setHighSchoolTopResultCentroids(List<HighSchoolTopResultCentroid> highSchoolTopResultCentroids) {
        this.highSchoolTopResultCentroids = highSchoolTopResultCentroids;
    }

    public List<MajorCentroidWithHobby> getMajorCentroidWithHobbies() {
        return majorCentroidWithHobbies;
    }

    public void setMajorCentroidWithHobbies(List<MajorCentroidWithHobby> majorCentroidWithHobbies) {
        this.majorCentroidWithHobbies = majorCentroidWithHobbies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MajorCentroid that = (MajorCentroid) o;
        return id == that.id &&
                majorId == that.majorId &&
                characteristic == that.characteristic &&
                entranceExamResultCentroidId == that.entranceExamResultCentroidId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, majorId, characteristic, entranceExamResultCentroidId);
    }
}
