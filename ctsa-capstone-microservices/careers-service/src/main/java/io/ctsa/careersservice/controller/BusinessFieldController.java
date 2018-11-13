package io.ctsa.careersservice.controller;

import io.ctsa.careersservice.exception.NotFoundInDatasetException;
import io.ctsa.careersservice.service.BusinessFieldService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin("*")
@RestController
public class BusinessFieldController {

    private final BusinessFieldService businessFieldService;

    public BusinessFieldController(BusinessFieldService businessFieldService) {
        this.businessFieldService = businessFieldService;
    }

    @GetMapping("/majors/{id}")
    public ResponseEntity findById(@PathVariable String id) {
        try {
            return ResponseEntity.status(OK)
                                 .body(businessFieldService.findById(Integer.parseInt(id))
                                                           .orElseThrow(NotFoundInDatasetException::new));
        } catch (NotFoundInDatasetException e) {
            return ResponseEntity.status(NOT_FOUND)
                                 .build();
        }
    }
}
