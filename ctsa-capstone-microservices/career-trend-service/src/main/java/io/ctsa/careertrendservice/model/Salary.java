package io.ctsa.careertrendservice.model;

import io.ctsa.careertrendservice.prediction.PredictionModel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Salary extends PredictionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int majorId;

    public Salary() {
        super();
    }
}
