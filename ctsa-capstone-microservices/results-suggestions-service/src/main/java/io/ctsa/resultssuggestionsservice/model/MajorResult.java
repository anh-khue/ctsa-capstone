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
public class MajorResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Integer personId;
    private int characteristic;
    private Integer entranceExamResultId;
    private int majorId;

    // Object Relational Mapping
    @Transient
    private Person person;
    @Transient
    private EntranceExamResult entranceExamResult;
    @Transient
    private Major major;
    @Transient
    private List<HighSchoolTopResult> highSchoolTopResults;
    @Transient
    private List<MajorResultWithHobby> majorResultWithHobbies;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public int getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(int characteristic) {
        this.characteristic = characteristic;
    }

    public Integer getEntranceExamResultId() {
        return entranceExamResultId;
    }

    public void setEntranceExamResultId(Integer entranceExamResultId) {
        this.entranceExamResultId = entranceExamResultId;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public EntranceExamResult getEntranceExamResult() {
        return entranceExamResult;
    }

    public void setEntranceExamResult(EntranceExamResult entranceExamResult) {
        this.entranceExamResult = entranceExamResult;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public List<HighSchoolTopResult> getHighSchoolTopResults() {
        return highSchoolTopResults;
    }

    public void setHighSchoolTopResults(List<HighSchoolTopResult> highSchoolTopResults) {
        this.highSchoolTopResults = highSchoolTopResults;
    }

    public List<MajorResultWithHobby> getMajorResultWithHobbies() {
        return majorResultWithHobbies;
    }

    public void setMajorResultWithHobbies(List<MajorResultWithHobby> majorResultWithHobbies) {
        this.majorResultWithHobbies = majorResultWithHobbies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MajorResult that = (MajorResult) o;
        return id == that.id &&
                characteristic == that.characteristic &&
                majorId == that.majorId &&
                Objects.equals(personId, that.personId) &&
                Objects.equals(entranceExamResultId, that.entranceExamResultId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personId, characteristic, entranceExamResultId, majorId);
    }
}
