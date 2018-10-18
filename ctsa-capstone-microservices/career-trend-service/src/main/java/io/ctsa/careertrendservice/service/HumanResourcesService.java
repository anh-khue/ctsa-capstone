package io.ctsa.careertrendservice.service;

import io.ctsa.careertrendservice.model.HumanResource;
import io.ctsa.careertrendservice.prediction.ExponentialSmoothingFormula;
import io.ctsa.careertrendservice.repository.HumanResourcesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HumanResourcesService {

    private final HumanResourcesRepository humanResourcesRepository;
    private final ExponentialSmoothingFormula exponentialSmoothingFormula;

    public HumanResourcesService(HumanResourcesRepository humanResourcesRepository,
                                 ExponentialSmoothingFormula exponentialSmoothingFormula) {
        this.humanResourcesRepository = humanResourcesRepository;
        this.exponentialSmoothingFormula = exponentialSmoothingFormula;
    }

    public List<HumanResource> getAll() {
        return humanResourcesRepository.findAll();
    }

    public Optional<HumanResource> getById(int humanResourcesId) {
        return humanResourcesRepository.findById(humanResourcesId);
    }

    public List<HumanResource> getAllByYear(int year) {
        return humanResourcesRepository.findByYear(year);
    }

    public HumanResource predict(int predictedYear, int majorId) {
        HumanResource nearestPredictionModel = humanResourcesRepository.findFirstByMajorIdOrderByYearDesc(majorId);

        HumanResource predictionModel = new HumanResource();
        predictionModel.setMajorId(majorId);
        predictionModel.setYear(predictedYear);

        predictionModel = exponentialSmoothingFormula.predict(nearestPredictionModel, predictionModel);

        return predictionModel;
    }

    public HumanResource getLatest(int majorId) {
        return humanResourcesRepository.findFirstByMajorIdOrderByYearDesc(majorId);
    }
}
