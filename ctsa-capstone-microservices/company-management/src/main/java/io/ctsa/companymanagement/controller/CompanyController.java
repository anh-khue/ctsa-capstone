package io.ctsa.companymanagement.controller;

import io.ctsa.companymanagement.model.Company;
import io.ctsa.companymanagement.model.Recruitment;
import io.ctsa.companymanagement.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@Controller
@CrossOrigin(origins = "*")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(value = "/companies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAll() {
        List<Company> companies = companyService.getAll();

        return !companies.isEmpty() ? status(OK).body(companies) :
                status(NO_CONTENT).build();
    }

    @GetMapping(value = "/companies/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getById(@PathVariable("id") int companyId) {
        return companyService.getById(companyId)
                .map(company -> status(OK).body(company))
                .orElseGet(status(NO_CONTENT)::build);
    }

    @GetMapping(value = "/companies/{id}/recruitment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllRecruitmentByCompanyId(@PathVariable("id") int companyId) {
        List<Recruitment> recruitment = companyService.getAllRecruitmentByCompanyId(companyId);

        return !recruitment.isEmpty() ? status(OK).body(recruitment) :
                status(NO_CONTENT).build();
    }

    @PostMapping(value = "/companies", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register(@RequestBody Company partner) {
        return ResponseEntity.status(HttpStatus.OK).body(partner);
    }
}
