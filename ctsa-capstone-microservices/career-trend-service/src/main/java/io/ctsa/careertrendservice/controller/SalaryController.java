package io.ctsa.careertrendservice.controller;

import io.ctsa.careertrendservice.service.SalaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class SalaryController {

    private final SalaryService salaryService;

    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @GetMapping("salaries/latest")
    public ResponseEntity getLatest(@RequestParam int majorId) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(salaryService.getLatest(majorId));
    }

    @GetMapping("salaries/predictions")
    public ResponseEntity getPrediction(@RequestParam int year, @RequestParam int majorId) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(salaryService.predict(year, majorId));
    }
}
