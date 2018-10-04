package io.anhkhue.ctsa.vietnamworksscraper.repository;

import io.anhkhue.ctsa.vietnamworksscraper.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LinkRepository extends JpaRepository<Link, Integer> {

    Optional<Link> findByUrl(String url);
}
