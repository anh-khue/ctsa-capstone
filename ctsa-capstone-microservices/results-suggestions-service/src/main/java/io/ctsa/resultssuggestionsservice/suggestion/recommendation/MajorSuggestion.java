package io.ctsa.resultssuggestionsservice.suggestion.recommendation;

import io.ctsa.resultssuggestionsservice.model.common.Major;

public class MajorSuggestion {

    private Major major;
    private double distance;

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
