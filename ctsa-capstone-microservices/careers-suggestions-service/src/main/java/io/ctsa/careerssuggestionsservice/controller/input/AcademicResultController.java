package io.ctsa.careerssuggestionsservice.controller.input;

import io.ctsa.careerssuggestionsservice.suggestion.recommendation.input.AcademicResult;
import io.ctsa.careerssuggestionsservice.suggestion.recommendation.MajorSuggestion;
import io.ctsa.careerssuggestionsservice.suggestion.recommendation.PupilSuggestion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = "*")
@RestController
public class AcademicResultController {

    private final PupilSuggestion pupilSuggestion;

    public AcademicResultController(PupilSuggestion pupilSuggestion) {
        this.pupilSuggestion = pupilSuggestion;
    }

    @PostMapping("/suggestions/pupils")
    public ResponseEntity inputHighSchoolTop(@RequestBody AcademicResult input) {
        List<MajorSuggestion> majorSuggestions = pupilSuggestion.suggest(input);
        return !majorSuggestions.isEmpty() ? ResponseEntity.status(OK).body(majorSuggestions) :
               ResponseEntity.status(NO_CONTENT).build();
    }
}