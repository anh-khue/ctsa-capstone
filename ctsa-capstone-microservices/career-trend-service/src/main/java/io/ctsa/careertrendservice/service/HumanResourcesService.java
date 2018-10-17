package io.ctsa.careertrendservice.service;

import io.ctsa.careertrendservice.model.HumanResources;
import io.ctsa.careertrendservice.repository.HumanResourcesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HumanResourcesService {
    private final HumanResourcesRepository humanResourcesRepository;

    public HumanResourcesService(HumanResourcesRepository humanResourcesRepository) {
        this.humanResourcesRepository = humanResourcesRepository;
    }

    public List<HumanResources> getAll() {
        return humanResourcesRepository.findAll();
    }

    public Optional<HumanResources> getById(int humanResourcesId) {
        return humanResourcesRepository.findById(humanResourcesId);
    }

    public List<HumanResources> getAllByYear(int year) {
        return humanResourcesRepository.findAllByYear(year);
    }
}
