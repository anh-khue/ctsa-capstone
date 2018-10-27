package io.ctsa.careertrendservice.prediction.heuristicsearch;

import io.ctsa.careertrendservice.prediction.PredictionModel;
import io.ctsa.careertrendservice.prediction.timeseries.ExponentialSmoothingFormula;

import java.util.List;

public class SmoothingAlphaOptimization extends SmoothingParameterOptimization {

    private final ExponentialSmoothingFormula smoothingFormula;

    private final List<PredictionModel> predictionModels;

    private final double beta;

    public SmoothingAlphaOptimization(ExponentialSmoothingFormula smoothingFormula,
                                      List<PredictionModel> predictionModels,
                                      double beta) {
        this.smoothingFormula = smoothingFormula;
        this.predictionModels = predictionModels;
        this.beta = beta;
    }

    @Override
    Double findMeanSquaredError(Double smoothingParameter) {
        List<PredictionModel> predictedModels = smoothingFormula.exponentialSmooth(predictionModels,
                                                                                   smoothingParameter,
                                                                                   beta);
        return predictedModels.stream()
                              .mapToDouble(model -> Math.pow(model.getActual() - model.getForecast(), 2))
                              .sum()
                / predictedModels.size();
    }
}
