package io.ctsa.careertrendservice.prediction;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

@Component
public class ExponentialSmoothingFormula {

    @Value("${ctsa.career.trend.default-alpha}")
    private double alpha;
    @Value("${ctsa.career.trend.default-beta}")
    private double beta;

    public <T extends PredictionModel> T predict(PredictionModel rootModel, T forecastModel) {
        forecastModel.setAlpha(alpha);
        forecastModel.setBeta(beta);

        double level = calculateLevel(rootModel);
        forecastModel.setLevel(level);

        double trend = calculateTrend(rootModel, level);
        forecastModel.setTrend(trend);

        double forecast = calculateForecast(level,
                                            trend,
                                            abs(rootModel.getYear() - forecastModel.getYear()));
        forecastModel.setForecast(forecast);

        return forecastModel;
    }

    public <T extends PredictionModel> List<T> exponentialSmooth(List<T> models) {
        for (int i = 0; i < models.size(); i++) {
            PredictionModel model = models.get(i);
            if (i == 0) {
                model.setLevel(model.getActual());
                model.setTrend(0.0);
                model.setForecast(calculateForecast(model.getLevel(), model.getTrend(), 0));
                model.setAlpha(alpha);
                model.setBeta(beta);
            } else {
                PredictionModel previousModel = models.get(i - 1);
                model = predict(previousModel, model);
//                model.setLevel(calculateLevel(previousModel));
//                model.setTrend(calculateTrend(previousModel, model.getLevel()));
//                model.setForecast(calculateForecast(model.getLevel(),
//                                                    model.getTrend(),
//                                                    model.getYear() - previousModel.getYear()));
//                model.setAlpha(alpha);
//                model.setBeta(beta);
            }
        }

        return models;
    }

    private double calculateLevel(PredictionModel rootModel) {
        return alpha * rootModel.getActual()
                + (1 - alpha) * (rootModel.getLevel() + rootModel.getTrend());
    }

    private double calculateTrend(PredictionModel rootModel, double currentLevel) {
        return beta * (currentLevel - rootModel.getLevel())
                + (1 - beta) * rootModel.getTrend();
    }

    private double calculateForecast(double level, double trend, int duration) {
        return level + duration * trend;
    }
}
