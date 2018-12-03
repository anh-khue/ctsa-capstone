package io.ctsa.careerssuggestionsservice.controller.common;

import io.ctsa.careerssuggestionsservice.service.common.EntranceExamSubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = "*")
@RestController
public class EntranceExamSubjectController {

    private final EntranceExamSubjectService entranceExamSubjectService;

    public EntranceExamSubjectController(EntranceExamSubjectService entranceExamSubjectService) {
        this.entranceExamSubjectService = entranceExamSubjectService;
    }

    @GetMapping("entrance-exam-subjects")
    public ResponseEntity getAll() {
        return ResponseEntity.status(OK)
                .body(entranceExamSubjectService.getAll());
    }
}
