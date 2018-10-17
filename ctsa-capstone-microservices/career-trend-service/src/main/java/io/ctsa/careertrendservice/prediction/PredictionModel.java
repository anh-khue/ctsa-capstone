package io.ctsa.careertrendservice.prediction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class PredictionModel {

    @NonNull
    private int year;
    private Double actual;
    private Double level;
    private Double trend;
    private Double forecast;
    private Double alpha;
    private Double beta;
    private String link;
}