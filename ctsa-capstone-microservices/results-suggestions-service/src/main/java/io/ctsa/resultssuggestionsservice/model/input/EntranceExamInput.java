package io.ctsa.resultssuggestionsservice.model.input;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "entrance_exam_input", schema = "ctsa_results_suggestions_db", catalog = "")
public class EntranceExamInput {

    private int id;
    private Collection<EntranceExamInputDetail> entranceExamInputDetails;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntranceExamInput that = (EntranceExamInput) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @OneToMany(mappedBy = "entranceExamInput")
    public Collection<EntranceExamInputDetail> getEntranceExamInputDetails() {
        return entranceExamInputDetails;
    }

    public void setEntranceExamInputDetails(Collection<EntranceExamInputDetail> entranceExamInputDetails) {
        this.entranceExamInputDetails = entranceExamInputDetails;
    }

    @OneToMany(mappedBy = "entranceExamInput")
    public Collection<SuggestedMajorInput> getSuggestedMajorInputs() {
        return suggestedMajorInputs;
    }

    public void setSuggestedMajorInputs(Collection<SuggestedMajorInput> suggestedMajorInputs) {
        this.suggestedMajorInputs = suggestedMajorInputs;
    }
}
