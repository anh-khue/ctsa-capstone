package io.ctsa.basedatasetservice.repository;

import io.ctsa.basedatasetservice.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<Keyword, Integer> {

}
