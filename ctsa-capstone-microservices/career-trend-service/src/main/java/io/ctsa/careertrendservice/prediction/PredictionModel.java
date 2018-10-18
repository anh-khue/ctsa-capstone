package io.ctsa.careertrendservice.prediction;

import lombok.*;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class PredictionModel {

    private int year;
    private Double actual;
    @NonNull
    private Double level;
    @NonNull
    private Double trend;
    @NonNull
    private Double forecast;
    @NonNull
    private double alpha;
    @NonNull
    private double beta;
    private String link;
}