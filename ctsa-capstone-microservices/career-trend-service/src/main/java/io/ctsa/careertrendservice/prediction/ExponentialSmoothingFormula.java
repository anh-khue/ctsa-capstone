package io.ctsa.careertrendservice.prediction;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExponentialSmoothingFormula {

    @Value("${ctsa.career.trend.default-alpha}")
    private double alpha;
    @Value("${ctsa.career.trend.default-beta}")
    private double beta;

    public List<PredictionModel> exponentialSmoothie(List<PredictionModel> models) {
        List<PredictionModel> result = new ArrayList<>();
        for (int i = 0; i < models.size(); i++) {
            PredictionModel model = models.get(i);
            if (i == 0) {
                model.setLevel(model.getActual());
                model.setTrend(0.0);
                model.setForecast(calculateForecast(model.getLevel(), 0, model.getTrend()));
            } else {
                PredictionModel previousModel = models.get(i - 1);
                model.setLevel(calculateLevel(previousModel));
                model.setTrend(calculateTrend(previousModel, model.getLevel()));
                model.setForecast(calculateForecast(model.getLevel(),
                        model.getYear() - previousModel.getYear(),
                        model.getTrend()));
            }
            result.add(model);
        }

        for (PredictionModel model : result) {
            System.out.println(model.getYear() + ":\n\tLevel: " + model.getLevel() + "\n\tTrend: " + model.getTrend() + "\n\tForecast: " + model.getForecast() + "\n");
        }

        return result;
    }

    private double calculateLevel(PredictionModel previousModel) {
        return alpha * previousModel.getActual()
                + (1 - alpha) * (previousModel.getLevel() + previousModel.getTrend());
    }

    private double calculateTrend(PredictionModel previousModel, double level) {
        return beta * (level - previousModel.getLevel())
                + (1 - beta) * previousModel.getTrend();
    }

    private double calculateForecast(double level, int time, double trend) {
        return level + time * trend;
    }
}
