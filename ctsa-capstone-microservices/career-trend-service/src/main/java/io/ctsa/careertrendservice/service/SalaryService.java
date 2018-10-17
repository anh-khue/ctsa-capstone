package io.ctsa.careertrendservice.service;

import io.ctsa.careertrendservice.model.Salary;
import io.ctsa.careertrendservice.repository.SalaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaryService {
    private final SalaryRepository salaryRepository;

    public SalaryService(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    public List<Salary> getAll() {
        return salaryRepository.findAll();
    }

    public Optional<Salary> getById(int salaryId) {
        return salaryRepository.findById(salaryId);
    }

    public List<Salary> getAllByYear(int year) {
        return salaryRepository.findAllByYear(year);
    }
}
