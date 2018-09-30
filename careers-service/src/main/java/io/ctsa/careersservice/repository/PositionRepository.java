package io.ctsa.careersservice.repository;

import io.ctsa.careersservice.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Integer> {

}
