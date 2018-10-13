package io.ctsa.warehouseservice.controller;

import io.ctsa.warehouseservice.model.Recruitment;
import io.ctsa.warehouseservice.service.RecruitmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    public RecruitmentController(RecruitmentService recruitmentService) {
        this.recruitmentService = recruitmentService;
    }

    @PostMapping("/recruitment")
    public ResponseEntity save(@RequestBody Recruitment recruitment) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(recruitmentService.saveRecruitment(recruitment));
    }
}
