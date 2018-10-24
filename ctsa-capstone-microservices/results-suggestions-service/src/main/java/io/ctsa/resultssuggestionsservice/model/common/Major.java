package io.ctsa.resultssuggestionsservice.model.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ctsa.resultssuggestionsservice.model.centroid.MajorCentroid;
import io.ctsa.resultssuggestionsservice.model.input.SuggestedMajorInput;
import io.ctsa.resultssuggestionsservice.model.sample.MajorSample;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Major {

    private int id;
    private String name;
    private String imageUrl;
    private String vietnamese;
    @JsonIgnore
    private Collection<MajorCentroid> majorCentroids;
    @JsonIgnore
    private Collection<MajorSample> majorSamples;
    @JsonIgnore
    private Collection<SuggestedMajorInput> suggestedMajorInputs;
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
    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
        Major major = (Major) o;
        return id == major.id &&
                Objects.equals(name, major.name) &&
                Objects.equals(imageUrl, major.imageUrl) &&
                Objects.equals(vietnamese, major.vietnamese);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, imageUrl, vietnamese);
    }

    @OneToMany(mappedBy = "major")
    public Collection<MajorCentroid> getMajorCentroids() {
        return majorCentroids;
    }

    public void setMajorCentroids(Collection<MajorCentroid> majorCentroids) {
        this.majorCentroids = majorCentroids;
    }

    @OneToMany(mappedBy = "major")
    public Collection<MajorSample> getMajorSamples() {
        return majorSamples;
    }

    public void setMajorSamples(Collection<MajorSample> majorSamples) {
        this.majorSamples = majorSamples;
    }

    @OneToMany(mappedBy = "major")
    public Collection<SuggestedMajorInput> getSuggestedMajorInputs() {
        return suggestedMajorInputs;
    }

    public void setSuggestedMajorInputs(Collection<SuggestedMajorInput> suggestedMajorInputs) {
        this.suggestedMajorInputs = suggestedMajorInputs;
    }
}
