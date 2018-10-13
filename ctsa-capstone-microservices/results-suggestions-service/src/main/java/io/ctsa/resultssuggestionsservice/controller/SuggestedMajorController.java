package io.ctsa.resultssuggestionsservice.controller;

import io.ctsa.resultssuggestionsservice.model.SuggestedMajor;
import io.ctsa.resultssuggestionsservice.suggestion.recommendation.MajorSuggestion;
import io.ctsa.resultssuggestionsservice.suggestion.recommendation.PupilSuggestion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
public class SuggestedMajorController {
    private final PupilSuggestion pupilSuggestion;

    public SuggestedMajorController(PupilSuggestion pupilSuggestion) {
        this.pupilSuggestion = pupilSuggestion;
    }

    @PostMapping("/suggestions")
    public ResponseEntity inputHighSchoolTop(@RequestBody SuggestedMajor input) {
        List<MajorSuggestion> majorSuggestions = pupilSuggestion.suggest(input);
        return !majorSuggestions.isEmpty() ? ResponseEntity.status(OK).body(majorSuggestions) :
                ResponseEntity.status(NO_CONTENT).build();
    }

    
}
