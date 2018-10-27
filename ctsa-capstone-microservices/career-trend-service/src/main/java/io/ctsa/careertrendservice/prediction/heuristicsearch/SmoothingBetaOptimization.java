package io.ctsa.careertrendservice.prediction.heuristicsearch;

import io.ctsa.careertrendservice.prediction.PredictionModel;
import io.ctsa.careertrendservice.prediction.timeseries.ExponentialSmoothingFormula;

import java.util.List;

public class SmoothingBetaOptimization extends SmoothingParameterOptimization {

    private final ExponentialSmoothingFormula smoothingFormula;

    private final List<PredictionModel> predictionModels;

    private final double alpha;

    public SmoothingBetaOptimization(ExponentialSmoothingFormula smoothingFormula,
                                     List<PredictionModel> predictionModels,
                                     double alpha) {
        this.smoothingFormula = smoothingFormula;
        this.predictionModels = predictionModels;
        this.alpha = alpha;
    }

    @Override
    Double findMeanSquaredError(Double smoothingParameter) {
        List<PredictionModel> predictedModels = smoothingFormula.exponentialSmooth(predictionModels,
                                                                                   alpha,
                                                                                   smoothingParameter);
        return predictedModels.stream()
                              .mapToDouble(model -> Math.pow(model.getActual() - model.getForecast(), 2))
                              .sum()
                / predictedModels.size();
    }
}
