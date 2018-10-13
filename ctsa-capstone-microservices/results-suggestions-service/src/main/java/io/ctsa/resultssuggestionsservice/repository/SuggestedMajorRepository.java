package io.ctsa.resultssuggestionsservice.repository;

import io.ctsa.resultssuggestionsservice.model.SuggestedMajor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestedMajorRepository extends JpaRepository<SuggestedMajor, Integer> {

}