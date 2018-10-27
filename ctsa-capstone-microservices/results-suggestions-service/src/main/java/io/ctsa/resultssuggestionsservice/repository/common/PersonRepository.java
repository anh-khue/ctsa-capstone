package io.ctsa.resultssuggestionsservice.repository.common;

import io.ctsa.resultssuggestionsservice.model.common.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
