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
