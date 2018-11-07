package io.ctsa.companymanagement.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.ctsa.companymanagement.exception.CompanyNotFoundException;
import io.ctsa.companymanagement.model.Company;
import io.ctsa.companymanagement.model.Recruitment;
import io.ctsa.companymanagement.repository.CompanyRepository;
import io.ctsa.companymanagement.repository.RecruitmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class CompanyService {

//    @Value("${ctsa.cloudinary.cloud-name}")
//    private String cloudName;
//    @Value("${ctsa.cloudinary.api-key}")
//    private String apiKey;
//    @Value("${ctsa.cloudinary.api-secret}")
//    private String apiSecret;

    private final static String cloudName = "ctsa-capstone";
    private final static String apiKey = "921697169711314";
    private final static String apiSecret = "7C3N-zpRTGpCga-_dM2JK-37GwE";
//    private final static String folder = "partner-logos";
//    private static String CLOUDINARY_URL = "cloudinary://" + apiKey + ":" + apiSecret + "@" + cloudName;

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

    public Page<Recruitment> getRecruitmentByCompanyId(int companyId, int pageNumber, int itemsPerPage) {
        Pageable pageable = PageRequest.of(pageNumber, itemsPerPage);
        return recruitmentRepository.findByCompanyId(pageable, companyId);
    }

    public Company create(Company partner) {
        companyRepository.saveAndFlush(partner);
        return partner;
    }

    public String generateLogoLink(MultipartFile logo) {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret));
        try {
            File file = handleMultipartFile(logo);
            if (file != null) {
                Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
                log.info((file.delete()) ? "Successfully deleted generated file." : "Failed to delete generated file.");
                return uploadResult.get("secure_url").toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private File handleMultipartFile(MultipartFile multipartFile) {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        if (!multipartFile.isEmpty()) {
            try {
                byte[] bytes = multipartFile.getBytes();
                FileUtils.writeByteArrayToFile(file, bytes);
            } catch (Exception e) {
                log.error("Failed to generate file from multipart.");
                e.printStackTrace();
                return null;
            }
        }
        return file;
    }

    public Company updateCompany(int partnerId, Company modifiedData) throws CompanyNotFoundException {
        return companyRepository.findById(partnerId)
                .map(company -> {
                    company.setName(modifiedData.getName());
                    company.setDescription(modifiedData.getDescription());
                    company.setEmail(modifiedData.getEmail());
                    company.setPhone(modifiedData.getPhone());
                    company.setAddress(modifiedData.getAddress());
                    company.setAdditionalInformation(modifiedData.getAdditionalInformation());
                    companyRepository.saveAndFlush(company);

                    return company;
                }).orElseThrow(CompanyNotFoundException::new);
    }
}
