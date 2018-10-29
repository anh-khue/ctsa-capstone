package io.ctsa.careersservice.repository;

import io.ctsa.careersservice.model.SkillType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SkillTypeRepository extends CrudRepository<SkillType, Integer> {

    List<SkillType> findByBusinessFieldId(int businessFieldId);
}
