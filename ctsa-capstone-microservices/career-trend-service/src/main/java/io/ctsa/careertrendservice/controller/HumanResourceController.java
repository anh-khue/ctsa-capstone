package io.ctsa.careertrendservice.controller;

import io.ctsa.careertrendservice.service.HumanResourcesService;
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

    @GetMapping("human-resources/latest")
    public ResponseEntity getPrediction(@RequestParam int majorId) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(humanResourcesService.getLatest(majorId));
    }

    @GetMapping("human-resources/predictions")
    public ResponseEntity getPrediction(@RequestParam int year, @RequestParam int majorId) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(humanResourcesService.predict(year, majorId));
    }
}
