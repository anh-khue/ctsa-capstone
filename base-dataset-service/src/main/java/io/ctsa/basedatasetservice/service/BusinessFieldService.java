package io.ctsa.basedatasetservice.service;

import io.ctsa.basedatasetservice.repository.BusinessFieldRepository;
import org.springframework.stereotype.Service;

@Service
public class BusinessFieldService {

    private final BusinessFieldRepository businessFieldRepository;

    public BusinessFieldService(BusinessFieldRepository businessFieldRepository) {
        this.businessFieldRepository = businessFieldRepository;
    }
}
