package io.ctsa.careertrendservice.service;

import io.ctsa.careertrendservice.model.SkillPredictionModel;
import io.ctsa.careertrendservice.model.SkillTrend;
import io.ctsa.careertrendservice.prediction.PredictionModel;
import io.ctsa.careertrendservice.prediction.storage.SmoothingParams;
import io.ctsa.careertrendservice.prediction.timeseries.ExponentialSmoothingFormula;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SkillsTrendService {

    private final SmoothingParamsService smoothingParamsService;

    private final ExponentialSmoothingFormula formula;

    public SkillsTrendService(SmoothingParamsService smoothingParamsService,
                              ExponentialSmoothingFormula formula) {
        this.smoothingParamsService = smoothingParamsService;
        this.formula = formula;
    }

    public List<SkillTrend> getSkillTrends(int duration,
                                           Map<Integer, List<SkillPredictionModel>> historicalMap) {
        List<SkillTrend> skillTrends = new ArrayList<>();
        historicalMap.forEach((skillId, historicalData) -> {
            SkillTrend trend = getSingleSkillTrend(duration, skillId, historicalData);
            skillTrends.add(trend);
        });

        skillTrends.sort((trend1, trend2) -> (int) (trend2.getPredictedNumberOfRecruitment()
                - trend1.getPredictedNumberOfRecruitment()));

        return skillTrends;
    }

    private SkillTrend getSingleSkillTrend(int duration,
                                           Integer skillId,
                                           List<SkillPredictionModel> historicalData) {
        String key = "SKILL_ID_" + skillId;
        SmoothingParams smoothingParams = this.findSmoothingParams(key, historicalData);

        SkillPredictionModel nearestHistoricalData = historicalData.get(historicalData.size() - 1);

        PredictionModel predictionModel = new SkillPredictionModel();
        predictionModel.setYear(nearestHistoricalData.getYear() + duration);
        predictionModel = formula.predict(nearestHistoricalData, predictionModel,
                                          smoothingParams.getAlpha(), smoothingParams.getBeta());

        SkillTrend skillTrend = new SkillTrend();
        skillTrend.setSkillId(skillId);
        skillTrend.setPredictedNumberOfRecruitment(predictionModel.getForecast());

        return skillTrend;
    }

    private SmoothingParams findSmoothingParams(String key, List<SkillPredictionModel> historicalData) {
        return smoothingParamsService.findSmoothingParams(key, formula, historicalData);
    }
}


