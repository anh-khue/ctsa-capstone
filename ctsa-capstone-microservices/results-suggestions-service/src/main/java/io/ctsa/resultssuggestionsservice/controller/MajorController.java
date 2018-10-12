package io.ctsa.resultssuggestionsservice.controller;

import io.ctsa.resultssuggestionsservice.model.Major;
import io.ctsa.resultssuggestionsservice.service.MajorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
public class MajorController {
    private final MajorService majorService;

    public MajorController(MajorService majorService) {
        this.majorService = majorService;
    }

    @GetMapping("/majors")
    public ResponseEntity getAll() {
        List<Major> majors = majorService.getAll();
        return !majors.isEmpty() ? ResponseEntity.status(OK).body(majors) :
                ResponseEntity.status(NO_CONTENT).build();
    }

    @GetMapping("/majors/{id}")
    public ResponseEntity getById(@PathVariable("id") int majorId) {
        return majorService.getById(majorId)
                .map(major -> ResponseEntity.status(OK).body(major))
                .orElseGet(ResponseEntity.status(NOT_FOUND)::build);
    }
}
