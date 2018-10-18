package io.ctsa.careertrendservice.service;

import io.ctsa.careertrendservice.model.Salary;
import io.ctsa.careertrendservice.prediction.ExponentialSmoothingFormula;
import io.ctsa.careertrendservice.repository.SalaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaryService {

    private final SalaryRepository salaryRepository;

    private final ExponentialSmoothingFormula exponentialSmoothingFormula;

    public SalaryService(SalaryRepository salaryRepository,
                         ExponentialSmoothingFormula exponentialSmoothingFormula) {
        this.salaryRepository = salaryRepository;
        this.exponentialSmoothingFormula = exponentialSmoothingFormula;
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

    public Salary predict(int predictedYear, int majorId) {
        Salary nearestPredictionModel = salaryRepository.findFirstByMajorIdOrderByYearDesc(majorId);

        Salary predictionModel = new Salary();
        predictionModel.setMajorId(majorId);
        predictionModel.setYear(predictedYear);

        predictionModel = exponentialSmoothingFormula.predict(nearestPredictionModel, predictionModel);

        return predictionModel;
    }

    public Salary getLatest(int majorId) {
        return salaryRepository.findFirstByMajorIdOrderByYearDesc(majorId);
    }
}
