package io.ctsa.careertrendservice.controller;

import io.ctsa.careertrendservice.model.SupportingInformation;
import io.ctsa.careertrendservice.service.SupportingInformationService;
import org.springframework.data.domain.Page;
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

    @GetMapping("supporting-information")
    public ResponseEntity getAllByMajorId(@RequestParam int majorId) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(supportingInformationService.getAllByMajorId(majorId));
    }

    @GetMapping("supporting-information/latest")
    public ResponseEntity getLatest(@RequestParam int majorId) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(supportingInformationService.getLatest(majorId));
    }

    @GetMapping("supporting-information/predictions")
    public ResponseEntity getPrediction(@RequestParam int year, @RequestParam int majorId) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(supportingInformationService.predict(year, majorId));
    }

    @GetMapping("supporting-information/predictions/series")
    public ResponseEntity getPredictionSeries(@RequestParam int endYear, @RequestParam int majorId) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(supportingInformationService.predictSeries(endYear, majorId));
    }

    @GetMapping("supporting-information/for-staff")
    public ResponseEntity getAllByMajorIdByPage(@RequestParam("major-id") int majorId,
                                                @RequestParam("item-per-page") int itemPerPage,
                                                @RequestParam("page") int page) {
        Page<SupportingInformation> humanResourcePage = supportingInformationService
                .getAllByMajorIdByPage(majorId, itemPerPage, page);
        return ResponseEntity.status(HttpStatus.OK)
                             .body(humanResourcePage.getContent());
    }

    @GetMapping("supporting-information/for-staff/page-counts")
    public ResponseEntity getAllByMajorIdPageCount(@RequestParam("major-id") int majorId,
                                                   @RequestParam("item-per-page") int itemPerPage) {
        Page<SupportingInformation> humanResourcePage = supportingInformationService
                .getAllByMajorIdByPage(majorId, itemPerPage, 1);
        return ResponseEntity.status(HttpStatus.OK)
                             .body(humanResourcePage.getTotalPages());
    }
}
