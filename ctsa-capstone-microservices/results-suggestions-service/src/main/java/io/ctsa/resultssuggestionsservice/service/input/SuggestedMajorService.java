package io.ctsa.resultssuggestionsservice.service.input;

import io.ctsa.resultssuggestionsservice.suggestion.recommendation.PupilSuggestion;
import org.springframework.stereotype.Service;

@Service
public class SuggestedMajorService {

    private final PupilSuggestion pupilSuggestion;

    public SuggestedMajorService(PupilSuggestion pupilSuggestion) {
        this.pupilSuggestion = pupilSuggestion;
    }
}
