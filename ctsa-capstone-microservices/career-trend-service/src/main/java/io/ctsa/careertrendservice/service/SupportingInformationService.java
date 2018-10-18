package io.ctsa.careertrendservice.service;

import io.ctsa.careertrendservice.model.SupportingInformation;
import io.ctsa.careertrendservice.prediction.ExponentialSmoothingFormula;
import io.ctsa.careertrendservice.repository.SupportingInformationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupportingInformationService {

    private final SupportingInformationRepository supportingInformationRepository;

    private final ExponentialSmoothingFormula exponentialSmoothingFormula;

    public SupportingInformationService(SupportingInformationRepository supportingInformationRepository,
                                        ExponentialSmoothingFormula exponentialSmoothingFormula) {
        this.supportingInformationRepository = supportingInformationRepository;
        this.exponentialSmoothingFormula = exponentialSmoothingFormula;
    }

    public List<SupportingInformation> getAll() {
        return supportingInformationRepository.findAll();
    }

    public Optional<SupportingInformation> getById(int supportingInformationId) {
        return supportingInformationRepository.findById(supportingInformationId);
    }

    public List<SupportingInformation> getAllByYear(int year) {
        return supportingInformationRepository.findAllByYear(year);
    }

    public SupportingInformation predict(int predictedYear, int majorId) {
        SupportingInformation nearestPredictionModel = supportingInformationRepository.findFirstByMajorIdOrderByYearDesc(majorId);

        SupportingInformation predictionModel = new SupportingInformation();
        predictionModel.setMajorId(majorId);
        predictionModel.setYear(predictedYear);

        predictionModel = exponentialSmoothingFormula.predict(nearestPredictionModel, predictionModel);
        predictionModel.setUnit(nearestPredictionModel.getUnit());

        return predictionModel;
    }

    public SupportingInformation getLatest(int majorId) {
        return supportingInformationRepository.findFirstByMajorIdOrderByYearDesc(majorId);
    }
}
