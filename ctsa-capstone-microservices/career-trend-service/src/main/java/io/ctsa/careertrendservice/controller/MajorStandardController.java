package io.ctsa.careertrendservice.controller;

import io.ctsa.careertrendservice.service.MajorStandardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = "*")
@RestController
public class MajorStandardController {

    private final MajorStandardService majorStandardService;

    public MajorStandardController(MajorStandardService majorStandardService) {
        this.majorStandardService = majorStandardService;
    }

    @GetMapping("/major_standards")
    public ResponseEntity findByMajorId(@RequestParam int majorId) {
        try {
            return ResponseEntity.status(OK)
                                 .body(majorStandardService.findByMajorId(majorId)
                                                           .orElseThrow(MajorStandardNotFoundException::new));
        } catch (MajorStandardNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                                 .build();
        }
    }
}


class MajorStandardNotFoundException extends Exception {

}
