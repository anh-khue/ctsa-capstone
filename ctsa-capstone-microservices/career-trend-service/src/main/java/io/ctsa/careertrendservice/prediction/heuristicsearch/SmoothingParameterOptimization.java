package io.ctsa.careertrendservice.prediction.heuristicsearch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class SmoothingParameterOptimization implements HillClimbingFunction<Double> {

    private double step = 0.001;

    @Override
    public Double getBestMove(Double currentState) {
        Double currentLeastMeanSquaredError = findMeanSquaredError(currentState);
        log.info("MSE of " + currentState + " is " + currentLeastMeanSquaredError);

        if (tryStepBack(currentState) < currentLeastMeanSquaredError) {
            log.info("Decided to move backward");
            return moveBackward(currentState);
        } else if (tryStepForward(currentState) < currentLeastMeanSquaredError) {
            log.info("Decided to move forward");
            return moveForward(currentState);
        }

        return currentState;
    }

    private Double tryStepBack(Double currentState) {
        log.info("Try to step backward");
        return findMeanSquaredError(currentState - step);
    }

    private Double tryStepForward(Double currentState) {
        log.info("Try to step forward");
        return findMeanSquaredError(currentState + step);
    }

    private Double moveBackward(Double currentState) {
        Double currentMeanSquaredError = findMeanSquaredError(currentState);
        log.info("MSE of " + currentState + " is " + currentMeanSquaredError);
        Double nextMeanSquaredError = findMeanSquaredError(currentState - step);

        if (currentState - step > 0 &&
                currentMeanSquaredError > nextMeanSquaredError) {
            return moveBackward(currentState - step);
        }

        return currentState;
    }

    private Double moveForward(Double currentState) {
        Double currentMeanSquaredError = findMeanSquaredError(currentState);
        log.info("MSE of " + currentState + " is " + currentMeanSquaredError);
        Double nextMeanSquaredError = findMeanSquaredError(currentState + step);

        if (currentState + step < 1 &&
                currentMeanSquaredError > nextMeanSquaredError) {
            return moveForward(currentState + step);
        }

        return currentState;
    }

    abstract Double findMeanSquaredError(Double smoothingParameter);
}
