package io.ctsa.careertrendservice.controller;

import io.ctsa.careertrendservice.service.SupportingInformationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class SupportingInformationController {

    private final SupportingInformationService supportingInformationService;

    public SupportingInformationController(SupportingInformationService supportingInformationService) {
        this.supportingInformationService = supportingInformationService;
    }

    @GetMapping("supporting-information/latest")
    public ResponseEntity getPrediction(@RequestParam int majorId) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(supportingInformationService.getLatest(majorId));
    }

    @GetMapping("supporting-information/predictions")
    public ResponseEntity getPrediction(@RequestParam int year, @RequestParam int majorId) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(supportingInformationService.predict(year, majorId));
    }
}
