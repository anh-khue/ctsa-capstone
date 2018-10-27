package io.ctsa.resultssuggestionsservice.model.sample;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "entrance_exam_sample", schema = "ctsa_results_suggestions_db", catalog = "")
public class EntranceExamSample {

    private int id;
    private Collection<EntranceExamSampleDetail> entranceExamSampleDetails;
    @JsonIgnore
    private Collection<MajorSample> majorSamples;
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
        EntranceExamSample that = (EntranceExamSample) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @OneToMany(mappedBy = "entranceExamSample")
    public Collection<EntranceExamSampleDetail> getEntranceExamSampleDetails() {
        return entranceExamSampleDetails;
    }

    public void setEntranceExamSampleDetails(Collection<EntranceExamSampleDetail> entranceExamSampleDetails) {
        this.entranceExamSampleDetails = entranceExamSampleDetails;
    }

    @OneToMany(mappedBy = "entranceExamSample")
    public Collection<MajorSample> getMajorSamples() {
        return majorSamples;
    }

    public void setMajorSamples(Collection<MajorSample> majorSamples) {
        this.majorSamples = majorSamples;
    }
}
