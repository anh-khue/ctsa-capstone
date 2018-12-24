package io.ctsa.careerssuggestionsservice.suggestion.recommendation.input;

public class EntranceExamResult {

    private int entranceExamSubjectId;
    private double mark;

    public EntranceExamResult() {
    }

    public EntranceExamResult(int entranceExamSubjectId, double mark) {
        this.entranceExamSubjectId = entranceExamSubjectId;
        this.mark = mark;
    }

    public int getEntranceExamSubjectId() {
        return entranceExamSubjectId;
    }

    public void setEntranceExamSubjectId(int entranceExamSubjectId) {
        this.entranceExamSubjectId = entranceExamSubjectId;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }
}
