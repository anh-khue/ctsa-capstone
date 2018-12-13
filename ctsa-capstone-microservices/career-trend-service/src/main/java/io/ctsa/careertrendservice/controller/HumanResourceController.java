package io.ctsa.careertrendservice.controller;

import io.ctsa.careertrendservice.model.HumanResource;
import io.ctsa.careertrendservice.service.HumanResourcesService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class HumanResourceController {

    private final HumanResourcesService humanResourcesService;

    public HumanResourceController(HumanResourcesService humanResourcesService) {
        this.humanResourcesService = humanResourcesService;
    }

    @GetMapping("human-resources")
    public ResponseEntity getAllByMajorId(@RequestParam int majorId) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(humanResourcesService.getAllByMajorId(majorId));
    }

    @GetMapping("human-resources/latest")
    public ResponseEntity getLatest(@RequestParam int majorId) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(humanResourcesService.getLatest(majorId));
    }

    @GetMapping("human-resources/predictions")
    public ResponseEntity getPrediction(@RequestParam int year, @RequestParam int majorId) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(humanResourcesService.predict(year, majorId));
    }

    @GetMapping("human-resources/predictions/series")
    public ResponseEntity getPredictionSeries(@RequestParam int endYear, @RequestParam int majorId) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(humanResourcesService.predictSeries(endYear, majorId));
    }

    @GetMapping("human-resources/for-staff")
    public ResponseEntity getAllByMajorIdByPage(@RequestParam("major-id") int majorId,
                                                @RequestParam("item-per-page") int itemPerPage,
                                                @RequestParam("page") int page) {
        Page<HumanResource> humanResourcePage = humanResourcesService
                .getAllByMajorIdByPage(majorId, itemPerPage, page);
        return ResponseEntity.status(HttpStatus.OK)
                             .body(humanResourcePage.getContent());
    }

    @GetMapping("human-resources/for-staff/page-counts")
    public ResponseEntity getAllByMajorIdPageCount(@RequestParam("major-id") int majorId,
                                                   @RequestParam("item-per-page") int itemPerPage) {
        Page<HumanResource> humanResourcePage = humanResourcesService
                .getAllByMajorIdByPage(majorId, itemPerPage, 1);
        return ResponseEntity.status(HttpStatus.OK)
                             .body(humanResourcePage.getTotalPages());
    }
}
