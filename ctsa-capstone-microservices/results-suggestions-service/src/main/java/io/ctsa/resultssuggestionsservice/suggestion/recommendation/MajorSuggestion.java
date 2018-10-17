package io.ctsa.resultssuggestionsservice.suggestion.recommendation;

import io.ctsa.resultssuggestionsservice.model.Major;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class MajorSuggestion {

    private Major major;
    private double distance;
}
