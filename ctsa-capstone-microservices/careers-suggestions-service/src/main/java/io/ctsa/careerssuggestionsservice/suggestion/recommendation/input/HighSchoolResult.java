package io.ctsa.careerssuggestionsservice.suggestion.recommendation.input;

public class HighSchoolResult {

    private int subjectId;
    private double mark;
    private int weight;

    public HighSchoolResult() {
    }

    public HighSchoolResult(int subjectId, double mark, int weight) {
        this.subjectId = subjectId;
        this.mark = mark;
        this.weight = weight;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
