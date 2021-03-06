package io.ctsa.careertrendservice.repository;

import io.ctsa.careertrendservice.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalaryRepository extends JpaRepository<Salary, Integer> {

    List<Salary> findByYear(int year);

    List<Salary> findByMajorIdOrderByYearAsc(int majorId);

    Salary findFirstByMajorIdOrderByYearDesc(int majorId);

    List<Salary> findByMajorId(int majorId);
}
