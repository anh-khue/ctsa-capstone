package io.ctsa.careerssuggestionsservice.repository.common;

import io.ctsa.careerssuggestionsservice.model.common.Major;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MajorRepository extends JpaRepository<Major, Integer> {

}
