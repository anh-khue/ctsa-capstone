package io.ctsa.careertrendservice.prediction.timeseries;

import io.ctsa.careertrendservice.prediction.PredictionModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.Math.abs;

@Component
public class ExponentialSmoothingFormula {

    public <T extends PredictionModel> T predict(PredictionModel rootModel, T forecastModel,
                                                 double alpha, double beta) {
        forecastModel.setAlpha(alpha);
        forecastModel.setBeta(beta);

        double level = calculateLevel(rootModel, alpha);
        forecastModel.setLevel(level);

        double trend = calculateTrend(rootModel, level, beta);
        forecastModel.setTrend(trend);

        double forecast = calculateForecast(level,
                                            trend,
                                            abs(rootModel.getYear() - forecastModel.getYear()));
        forecastModel.setForecast(forecast);

        return forecastModel;
    }

    public <T extends PredictionModel> List<T> exponentialSmooth(List<T> models, double alpha, double beta) {
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
                model = predict(previousModel, model, alpha, beta);
            }
        }

        return models;
    }

    private double calculateLevel(PredictionModel rootModel, double alpha) {
        return alpha * rootModel.getActual()
                + (1 - alpha) * (rootModel.getLevel() + rootModel.getTrend());
    }

    private double calculateTrend(PredictionModel rootModel, double currentLevel, double beta) {
        return beta * (currentLevel - rootModel.getLevel())
                + (1 - beta) * rootModel.getTrend();
    }

    private double calculateForecast(double level, double trend, int duration) {
        return level + duration * trend;
    }
}
