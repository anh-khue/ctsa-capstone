package io.ctsa.careertrendservice.prediction.heuristicsearch.function;

import org.springframework.beans.factory.annotation.Value;

public abstract class SmoothingParameterOptimization implements HillClimbingFunction<Double> {

    @Value("${prediction.heuristicsearch.hillclimbing.step}")
    protected double step;

    @Override
    public Double getBestMove(Double currentState) {
        Double currentLeastMeanSquaredError = findMeanSquaredError(currentState);

        if (tryStepBack(currentState) < currentLeastMeanSquaredError) {
            return moveBackward(currentState);
        } else if (tryStepForward(currentState) < currentLeastMeanSquaredError) {
            return moveForward(currentState);
        }

        return currentState;
    }

    private Double tryStepBack(Double currentState) {
        return findMeanSquaredError(currentState - step);
    }

    private Double tryStepForward(Double currentState) {
        return findMeanSquaredError(currentState + step);
    }

    private Double moveBackward(Double currentState) {
        Double currentMeanSquaredError = findMeanSquaredError(currentState);
        Double nextMeanSquaredError = findMeanSquaredError(currentState - step);

        if (currentState > 0 &&
                currentMeanSquaredError > nextMeanSquaredError) {
            return moveBackward(currentState - step);
        }

        return currentState;
    }

    private Double moveForward(Double currentState) {
        Double currentMeanSquaredError = findMeanSquaredError(currentState);
        Double nextMeanSquaredError = findMeanSquaredError(currentState + step);

        if (currentState > 0 &&
                currentMeanSquaredError > nextMeanSquaredError) {
            return moveForward(currentState + step);
        }

        return currentState;
    }

    abstract Double findMeanSquaredError(Double smoothingParameter);
}
