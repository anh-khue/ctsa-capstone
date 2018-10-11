package io.ctsa.resultssuggestionsservice.repository;

import io.ctsa.resultssuggestionsservice.model.MajorResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MajorResultRepository extends JpaRepository<MajorResult, Integer> {

    List<MajorResult> findByMajorId(int majorId);
}
