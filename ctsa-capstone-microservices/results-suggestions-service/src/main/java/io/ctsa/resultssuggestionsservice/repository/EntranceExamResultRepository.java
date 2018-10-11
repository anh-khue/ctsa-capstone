package io.ctsa.resultssuggestionsservice.repository;

import io.ctsa.resultssuggestionsservice.model.EntranceExamResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntranceExamResultRepository extends JpaRepository<EntranceExamResult, Integer> {

}
