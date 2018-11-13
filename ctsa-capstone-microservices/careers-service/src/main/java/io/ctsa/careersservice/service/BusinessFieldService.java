package io.ctsa.careersservice.service;

import io.ctsa.careersservice.model.BusinessField;
import io.ctsa.careersservice.repository.BusinessFieldRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusinessFieldService {

    private final BusinessFieldRepository businessFieldRepository;

    public BusinessFieldService(BusinessFieldRepository businessFieldRepository) {
        this.businessFieldRepository = businessFieldRepository;
    }

    public Optional<BusinessField> findById(Integer id) {
        return businessFieldRepository.findById(id);
    }
}
