package io.ctsa.careertrendservice.model;

import io.ctsa.careertrendservice.prediction.PredictionModel;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class HumanResources extends PredictionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private int careerId;

    public HumanResources() {
        super();
    }
}
