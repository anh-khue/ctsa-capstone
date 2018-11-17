package io.ctsa.warehouseservice.model;

import lombok.Data;

@Data
public class SkillPredictionModel {

    private int year;
    private int actual;
    private Double level;
    private Double trend;
    private Double forecast;
    private Double alpha;
    private Double beta;
    private String link;

    public SkillPredictionModel(int month, int actual) {
        this.year = month;
        this.actual = actual;
        this.level = 0.0;
        this.trend = 0.0;
        this.forecast = 0.0;
        this.alpha = 0.0;
        this.beta = 0.0;
        this.link = "";
    }
}
