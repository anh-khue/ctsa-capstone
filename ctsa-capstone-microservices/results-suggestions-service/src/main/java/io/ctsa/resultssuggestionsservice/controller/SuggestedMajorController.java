package io.ctsa.resultssuggestionsservice.controller;

import io.ctsa.resultssuggestionsservice.model.SuggestedMajor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuggestedMajorController {

    @PostMapping("/suggestions")
    public ResponseEntity inputHighSchoolTop(@RequestBody SuggestedMajor input) {
        return ResponseEntity.status(HttpStatus.OK)
                             .build();
    }
}
