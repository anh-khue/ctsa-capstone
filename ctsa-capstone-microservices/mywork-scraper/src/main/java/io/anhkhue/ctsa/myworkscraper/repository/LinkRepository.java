package io.anhkhue.ctsa.myworkscraper.repository;

import io.anhkhue.ctsa.myworkscraper.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LinkRepository extends JpaRepository<Link, Integer> {

    Optional<Link> findByUrl(String url);
}
