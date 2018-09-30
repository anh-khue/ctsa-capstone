package io.ctsa.careersservice.service;

import io.ctsa.careersservice.repository.BusinessFieldRepository;
import org.springframework.stereotype.Service;

@Service
public class BusinessFieldService {

    private final BusinessFieldRepository businessFieldRepository;

    public BusinessFieldService(BusinessFieldRepository businessFieldRepository) {
        this.businessFieldRepository = businessFieldRepository;
    }
}
