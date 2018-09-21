package io.ctsa.basedatasetservice.repository;

import io.ctsa.basedatasetservice.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, Integer> {

    List<Keyword> findByIsSynonym(boolean isSynonym);

    List<Keyword> findByMainKeywordId(int id);

    List<Keyword> findByPushedToElasticsearch(boolean pushedToElasticsearch);
}
