package io.ctsa.careertrendservice.service;

import io.ctsa.careertrendservice.model.PositionPredictionModel;
import io.ctsa.careertrendservice.prediction.PredictionModel;
import io.ctsa.careertrendservice.prediction.storage.SmoothingParams;
import io.ctsa.careertrendservice.prediction.timeseries.ExponentialSmoothingFormula;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionTrendService {

    private final SmoothingParamsService smoothingParamsService;

    private final ExponentialSmoothingFormula formula;

    public PositionTrendService(SmoothingParamsService smoothingParamsService,
                                ExponentialSmoothingFormula formula) {
        this.smoothingParamsService = smoothingParamsService;
        this.formula = formula;
    }

    public PositionPredictionModel getPositionPrediction(int duration,
                                                         Integer positionId,
                                                         List<PositionPredictionModel> historicalData) {
        String key = "SKILL_ID_" + positionId;
        SmoothingParams smoothingParams = this.findSmoothingParams(key, historicalData);

        PositionPredictionModel nearestHistoricalData = historicalData.get(historicalData.size() - 1);

        PredictionModel predictionModel = new PositionPredictionModel();
        predictionModel.setYear(nearestHistoricalData.getYear() + duration);
        predictionModel = formula.predict(nearestHistoricalData, predictionModel,
                                          smoothingParams.getAlpha(), smoothingParams.getBeta());
        return (PositionPredictionModel) predictionModel;
    }

    private SmoothingParams findSmoothingParams(String key,
                                                List<PositionPredictionModel> historicalData) {
        return smoothingParamsService.findSmoothingParams(key, formula, historicalData);
    }
}
