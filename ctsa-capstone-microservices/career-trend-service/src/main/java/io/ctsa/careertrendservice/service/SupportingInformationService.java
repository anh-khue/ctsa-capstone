package io.ctsa.careertrendservice.service;

import io.ctsa.careertrendservice.model.SupportingInformation;
import io.ctsa.careertrendservice.repository.SupportingInformationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupportingInformationService {
    private final SupportingInformationRepository supportingInformationRepository;

    public SupportingInformationService(SupportingInformationRepository supportingInformationRepository) {
        this.supportingInformationRepository = supportingInformationRepository;
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
}
