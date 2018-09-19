package io.ctsa.basedatasetservice.repository;

import io.ctsa.basedatasetservice.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Integer> {

}
