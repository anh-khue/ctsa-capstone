package io.ctsa.careertrendservice.prediction.storage;

import java.io.Serializable;

public class SmoothingParams implements Serializable {

    private double alpha;
    private double beta;
//    private double meanSquaredError;

    public SmoothingParams(double alpha, double beta) {
        this.alpha = alpha;
        this.beta = beta;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }
}
