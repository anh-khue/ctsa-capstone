package io.ctsa.resultssuggestionsservice.model.common;

import io.ctsa.resultssuggestionsservice.model.centroid.HighSchoolTopCentroid;
import io.ctsa.resultssuggestionsservice.model.input.HighSchoolTopInput;
import io.ctsa.resultssuggestionsservice.model.sample.HighSchoolTopSample;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "high_school_subject", schema = "ctsa_results_suggestions_db", catalog = "")
public class HighSchoolSubject {

    private int id;
    private String name;
    private String vietnamese;
    private Collection<HighSchoolTopCentroid> highSchoolTopCentroids;
    private Collection<HighSchoolTopInput> highSchoolTopInputs;
    private Collection<HighSchoolTopSample> highSchoolTopSamples;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HighSchoolSubject that = (HighSchoolSubject) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(vietnamese, that.vietnamese);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, vietnamese);
    }

    @OneToMany(mappedBy = "highSchoolSubject")
    public Collection<HighSchoolTopCentroid> getHighSchoolTopCentroids() {
        return highSchoolTopCentroids;
    }

    public void setHighSchoolTopCentroids(Collection<HighSchoolTopCentroid> highSchoolTopCentroids) {
        this.highSchoolTopCentroids = highSchoolTopCentroids;
    }

    @OneToMany(mappedBy = "highSchoolSubject")
    public Collection<HighSchoolTopInput> getHighSchoolTopInputs() {
        return highSchoolTopInputs;
    }

    public void setHighSchoolTopInputs(Collection<HighSchoolTopInput> highSchoolTopInputs) {
        this.highSchoolTopInputs = highSchoolTopInputs;
    }

    @OneToMany(mappedBy = "highSchoolSubject")
    public Collection<HighSchoolTopSample> getHighSchoolTopSamples() {
        return highSchoolTopSamples;
    }

    public void setHighSchoolTopSamples(Collection<HighSchoolTopSample> highSchoolTopSamples) {
        this.highSchoolTopSamples = highSchoolTopSamples;
    }
}
