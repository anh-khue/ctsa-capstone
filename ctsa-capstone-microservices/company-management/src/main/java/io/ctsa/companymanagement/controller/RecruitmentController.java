package io.ctsa.companymanagement.controller;

import io.ctsa.companymanagement.exception.RecruitmentNotFoundException;
import io.ctsa.companymanagement.model.Recruitment;
import io.ctsa.companymanagement.service.RecruitmentService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.*;

@Controller
@CrossOrigin(origins = "*")
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    public RecruitmentController(RecruitmentService recruitmentService) {
        this.recruitmentService = recruitmentService;
    }

    @GetMapping(value = "/recruitment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAll() {
        List<Recruitment> recruitment = recruitmentService.getAll();
        return !recruitment.isEmpty() ? status(OK).body(recruitment) :
                status(NO_CONTENT).build();
    }

    @GetMapping(value = "/recruitment/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getById(@PathVariable("id") int recruitmentId) throws RecruitmentNotFoundException {
        recruitmentService.updateViewCount(recruitmentId);
        return recruitmentService.getById(recruitmentId)
                .map(recruitment -> status(OK).body(recruitment))
                .orElseGet(status(NO_CONTENT)::build);
    }

    @PostMapping(value = "/recruitment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody Recruitment modifiedData) {
        Recruitment recruitment = recruitmentService.create(modifiedData);
        return recruitment != null ? status(CREATED).body(recruitment) :
                status(CONFLICT).build();
    }

    @PutMapping(value = "/recruitment/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateRecruitment(@PathVariable("id") int recruitmentId,
                                            @RequestBody Recruitment modifiedData) {
        try {
            return status(OK).body(recruitmentService.updateRecruitment(recruitmentId, modifiedData));
        } catch (RecruitmentNotFoundException e) {
            return status(NO_CONTENT).build();
        }
    }
}
