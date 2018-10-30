package io.ctsa.companymanagement.service;

import io.ctsa.companymanagement.model.Company;
import io.ctsa.companymanagement.model.Recruitment;
import io.ctsa.companymanagement.repository.CompanyRepository;
import io.ctsa.companymanagement.repository.RecruitmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final RecruitmentRepository recruitmentRepository;

    public CompanyService(CompanyRepository companyRepository, RecruitmentRepository recruitmentRepository) {
        this.companyRepository = companyRepository;
        this.recruitmentRepository = recruitmentRepository;
    }

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Optional<Company> getById(int companyId) {
        return companyRepository.findById(companyId);
    }

    public List<Recruitment> getAllRecruitmentByCompanyId(int companyId) {
        return recruitmentRepository.findAllByCompanyId(companyId);
    }
}
