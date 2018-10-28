package io.ctsa.careersservice.repository;

import io.ctsa.careersservice.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KeywordRepository extends JpaRepository<Keyword, Integer> {

    List<Keyword> findByIsSynonym(boolean isSynonym);

    List<Keyword> findByMainKeywordId(int id);

    List<Keyword> findByPushedToElasticsearch(boolean pushedToElasticsearch);

    Optional<Keyword> findByWord(String word);
}
