package io.ctsa.resultsservice.repository;

import io.ctsa.resultsservice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
