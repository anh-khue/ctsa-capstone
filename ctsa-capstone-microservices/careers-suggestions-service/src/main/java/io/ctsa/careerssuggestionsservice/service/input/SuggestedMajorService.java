package io.ctsa.careerssuggestionsservice.service.input;

import io.ctsa.careerssuggestionsservice.suggestion.recommendation.PupilSuggestion;
import org.springframework.stereotype.Service;

@Service
public class SuggestedMajorService {

    private final PupilSuggestion pupilSuggestion;

    public SuggestedMajorService(PupilSuggestion pupilSuggestion) {
        this.pupilSuggestion = pupilSuggestion;
    }
}
