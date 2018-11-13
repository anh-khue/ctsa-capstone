package io.ctsa.careertrendservice.repository;

import io.ctsa.careertrendservice.model.MajorStandard;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MajorStandardRepository extends CrudRepository<MajorStandard, Integer> {

    Optional<MajorStandard> findByMajorId(int majorId);
}
