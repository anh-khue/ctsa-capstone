package io.ctsa.resultssuggestionsservice.controller;

import com.sun.org.apache.regexp.internal.RE;
import io.ctsa.resultssuggestionsservice.model.HighSchoolSubject;
import io.ctsa.resultssuggestionsservice.service.HighSchoolSubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = "*")
@RestController
public class HighSchoolSubjectController {

    private final HighSchoolSubjectService highSchoolSubjectService;

    public HighSchoolSubjectController(HighSchoolSubjectService highSchoolSubjectService) {
        this.highSchoolSubjectService = highSchoolSubjectService;
    }

    @GetMapping("/high-school-subjects")
    public ResponseEntity getAll() {
        List<HighSchoolSubject> subjects = highSchoolSubjectService.getAll();
        return !subjects.isEmpty() ? ResponseEntity.status(OK).body(subjects) :
               ResponseEntity.status(NO_CONTENT).build();
    }
}
