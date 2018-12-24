package io.ctsa.careerssuggestionsservice.suggestion.recommendation.input;

import java.util.Collection;

public class AcademicResult {

    private double characteristic;
    private int majorId;
    private Double highSchoolAverage;
    private Collection<EntranceExamResult> entranceExamResults;
    private Collection<HighSchoolResult> highSchoolResults;

    public AcademicResult() {
    }

    public AcademicResult(double characteristic,
                          int majorId,
                          Double highSchoolAverage,
                          Collection<EntranceExamResult> entranceExamResults,
                          Collection<HighSchoolResult> highSchoolResults) {
        this.characteristic = characteristic;
        this.majorId = majorId;
        this.highSchoolAverage = highSchoolAverage;
        this.entranceExamResults = entranceExamResults;
        this.highSchoolResults = highSchoolResults;
    }

    public double getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(double characteristic) {
        this.characteristic = characteristic;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public Double getHighSchoolAverage() {
        return highSchoolAverage;
    }

    public void setHighSchoolAverage(Double highSchoolAverage) {
        this.highSchoolAverage = highSchoolAverage;
    }

    public Collection<EntranceExamResult> getEntranceExamResults() {
        return entranceExamResults;
    }

    public void setEntranceExamResults(Collection<EntranceExamResult> entranceExamResults) {
        this.entranceExamResults = entranceExamResults;
    }

    public Collection<HighSchoolResult> getHighSchoolResults() {
        return highSchoolResults;
    }

    public void setHighSchoolResults(Collection<HighSchoolResult> highSchoolResults) {
        this.highSchoolResults = highSchoolResults;
    }
}
