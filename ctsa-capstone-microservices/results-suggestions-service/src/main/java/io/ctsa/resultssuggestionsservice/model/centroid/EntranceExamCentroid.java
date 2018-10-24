package io.ctsa.resultssuggestionsservice.model.centroid;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ctsa.resultssuggestionsservice.model.sample.EntranceExamSample;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "entrance_exam_centroid", schema = "ctsa_results_suggestions_db", catalog = "")
public class EntranceExamCentroid {

    private int id;
    private Collection<EntranceExamCentroidDetail> entranceExamCentroidDetails;
    @JsonIgnore
    private Collection<MajorCentroid> majorCentroids;

    public EntranceExamCentroid() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntranceExamCentroid that = (EntranceExamCentroid) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @OneToMany(mappedBy = "entranceExamCentroid", fetch = FetchType.EAGER)
    public Collection<EntranceExamCentroidDetail> getEntranceExamCentroidDetails() {
        return entranceExamCentroidDetails;
    }

    public void setEntranceExamCentroidDetails(Collection<EntranceExamCentroidDetail> entranceExamCentroidDetails) {
        this.entranceExamCentroidDetails = entranceExamCentroidDetails;
    }

    @OneToMany(mappedBy = "entranceExamCentroid")
    public Collection<MajorCentroid> getMajorCentroids() {
        return majorCentroids;
    }

    public void setMajorCentroids(Collection<MajorCentroid> majorCentroids) {
        this.majorCentroids = majorCentroids;
    }
}
