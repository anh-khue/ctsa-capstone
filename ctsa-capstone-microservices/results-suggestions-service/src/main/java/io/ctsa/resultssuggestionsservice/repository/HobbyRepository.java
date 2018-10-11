package io.ctsa.resultssuggestionsservice.repository;

import io.ctsa.resultssuggestionsservice.model.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyRepository extends JpaRepository<Hobby, Integer> {

}
