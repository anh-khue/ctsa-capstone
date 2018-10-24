package io.ctsa.resultssuggestionsservice.controller.input;

import io.ctsa.resultssuggestionsservice.model.input.SuggestedMajorInput;
import io.ctsa.resultssuggestionsservice.suggestion.recommendation.MajorSuggestion;
import io.ctsa.resultssuggestionsservice.suggestion.recommendation.PupilSuggestion;
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
public class SuggestedMajorInputController {

    private final PupilSuggestion pupilSuggestion;

    public SuggestedMajorInputController(PupilSuggestion pupilSuggestion) {
        this.pupilSuggestion = pupilSuggestion;
    }

    @PostMapping("/suggestions/pupils")
    public ResponseEntity inputHighSchoolTop(@RequestBody SuggestedMajorInput input) {
        List<MajorSuggestion> majorSuggestions = pupilSuggestion.suggest(input);
        return !majorSuggestions.isEmpty() ? ResponseEntity.status(OK).body(majorSuggestions) :
               ResponseEntity.status(NO_CONTENT).build();
    }
}