package io.ctsa.companymanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ctsa.companymanagement.exception.CompanyNotFoundException;
import io.ctsa.companymanagement.model.Company;
import io.ctsa.companymanagement.model.Recruitment;
import io.ctsa.companymanagement.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;

@Controller
@CrossOrigin(origins = "*")
public class CompanyController {

    private static final int ITEMS_PER_PAGE = 5;

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(value = "/companies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAll() {
        List<Company> companies = companyService.getAll();
        return !companies.isEmpty() ? status(OK).body(companies)
                : status(NO_CONTENT).build();
    }

    @GetMapping(value = "/companies/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getById(@PathVariable("id") int companyId) {
        return companyService.getById(companyId)
                .map(company -> status(OK).body(company))
                .orElseGet(status(NO_CONTENT)::build);
    }

    @GetMapping(value = "/companies/{id}/recruitment/pages/{pageNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllRecruitmentByCompanyId(@PathVariable("id") int companyId,
                                                       @PathVariable("pageNumber") int pageNumber,
                                                       @RequestParam(value = "status", required = false) Integer published) {
        Page<Recruitment> recruitment;
        if (null != published) {
            recruitment = companyService.getByCompanyIdAndPublished(companyId, published, pageNumber, ITEMS_PER_PAGE);
        } else {
            recruitment = companyService.getRecruitmentByCompanyId(companyId, pageNumber, ITEMS_PER_PAGE);
        }
        return recruitment != null ? status(OK).body(recruitment)
                : status(NO_CONTENT).build();
    }

    @PostMapping(value = "/companies", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity create(@RequestParam("partner") String json, @RequestParam("file") MultipartFile file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Company partner = mapper.readValue(json, Company.class);
        partner.setLogo(companyService.generateLogoLink(file));
        partner.setAccountId(1);
        partner = companyService.create(partner);

        return partner != null ? status(CREATED).body(partner) : status(CONFLICT).build();
    }

    @PutMapping(value = "/companies/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateCompany(@PathVariable("id") int partnerId,
                                        @RequestBody Company modifiedData) {
        try {
            return status(OK).body(companyService.updateCompany(partnerId, modifiedData));
        } catch (CompanyNotFoundException e) {
            return status(NO_CONTENT).build();
        }
    }

    @PutMapping(value = "/companies/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity updateLogo(@PathVariable("id") int partnerId,
                                     @RequestParam("file") MultipartFile file) {
        return companyService.getById(partnerId)
                .map(partner -> {
                    partner.setLogo(companyService.generateLogoLink(file));
                    return status(OK).body(partner);
                }).orElseGet(status(NO_CONTENT)::build);
    }
}
