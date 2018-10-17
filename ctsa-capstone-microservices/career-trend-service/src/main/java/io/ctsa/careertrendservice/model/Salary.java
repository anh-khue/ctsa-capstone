package io.ctsa.careertrendservice.model;

import io.ctsa.careertrendservice.prediction.PredictionModel;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Salary extends PredictionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private int careerId;

    public Salary() {
        super();
    }
}
