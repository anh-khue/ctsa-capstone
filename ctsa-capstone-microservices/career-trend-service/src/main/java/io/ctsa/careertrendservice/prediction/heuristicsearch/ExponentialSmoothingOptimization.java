package io.ctsa.careertrendservice.prediction.heuristicsearch;

import org.springframework.stereotype.Component;

@Component
public class ExponentialSmoothingOptimization implements HillClimbingFunction<Double> {

    @Override
    public Double getBestMove(Double currentState) {
        return null;
    }
}
