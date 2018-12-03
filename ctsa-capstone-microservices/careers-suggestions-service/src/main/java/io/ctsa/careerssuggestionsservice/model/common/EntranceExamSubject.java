package io.ctsa.careerssuggestionsservice.model.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ctsa.careerssuggestionsservice.model.sample.EntranceExamSampleDetail;
import io.ctsa.careerssuggestionsservice.model.centroid.EntranceExamCentroidDetail;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "entrance_exam_subject", schema = "ctsa_career_suggestions_db")
public class EntranceExamSubject {

    private int id;
    private String name;
    private String vietnamese;
    private boolean isRequired;
    @JsonIgnore
    private Collection<EntranceExamCentroidDetail> entranceExamCentroidDetails;
    @JsonIgnore
    private Collection<EntranceExamSampleDetail> entranceExamSampleDetails;

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "vietnamese")
    public String getVietnamese() {
        return vietnamese;
    }

    public void setVietnamese(String vietnamese) {
        this.vietnamese = vietnamese;
    }

    @Basic
    @Column(name = "is_required")
    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntranceExamSubject that = (EntranceExamSubject) o;
        return id == that.id &&
                isRequired == that.isRequired &&
                Objects.equals(name, that.name) &&
                Objects.equals(vietnamese, that.vietnamese);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, vietnamese, isRequired);
    }

    @OneToMany(mappedBy = "entranceExamSubject")
    public Collection<EntranceExamCentroidDetail> getEntranceExamCentroidDetails() {
        return entranceExamCentroidDetails;
    }

    public void setEntranceExamCentroidDetails(Collection<EntranceExamCentroidDetail> entranceExamCentroidDetails) {
        this.entranceExamCentroidDetails = entranceExamCentroidDetails;
    }

    @OneToMany(mappedBy = "entranceExamSubject")
    public Collection<EntranceExamSampleDetail> getEntranceExamSampleDetails() {
        return entranceExamSampleDetails;
    }

    public void setEntranceExamSampleDetails(Collection<EntranceExamSampleDetail> entranceExamSampleDetails) {
        this.entranceExamSampleDetails = entranceExamSampleDetails;
    }
}
