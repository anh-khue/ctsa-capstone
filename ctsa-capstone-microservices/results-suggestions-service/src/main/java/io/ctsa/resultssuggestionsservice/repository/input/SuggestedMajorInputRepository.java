package io.ctsa.resultssuggestionsservice.repository.input;

import io.ctsa.resultssuggestionsservice.model.input.SuggestedMajorInput;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestedMajorInputRepository extends JpaRepository<SuggestedMajorInput, Integer> {

}