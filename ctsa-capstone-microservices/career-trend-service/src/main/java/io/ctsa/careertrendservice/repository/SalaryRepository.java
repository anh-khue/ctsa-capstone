package io.ctsa.careertrendservice.repository;

import io.ctsa.careertrendservice.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalaryRepository extends JpaRepository<Salary, Integer> {
    List<Salary> findAllByYear(int year);

    List<Salary> findAllByMajorIdOrderByYearAsc(int majorId);

    Salary findFirstByMajorIdOrderByYearDesc(int majorId);
}
