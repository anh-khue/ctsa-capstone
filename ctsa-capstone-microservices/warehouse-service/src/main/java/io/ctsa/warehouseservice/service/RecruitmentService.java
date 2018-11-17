package io.ctsa.warehouseservice.service;

import io.ctsa.warehouseservice.model.PositionPredictionModel;
import io.ctsa.warehouseservice.model.Recruitment;
import io.ctsa.warehouseservice.repository.RecruitmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

    public RecruitmentService(RecruitmentRepository recruitmentRepository) {
        this.recruitmentRepository = recruitmentRepository;
    }

    public Recruitment saveRecruitment(Recruitment recruitment) {
        recruitmentRepository.save(recruitment);
        recruitmentRepository.flush();
        return recruitment;
    }

    public List<PositionPredictionModel> findHistoricalDataByPositionId(int positionId) {
        LocalDate currentDay = LocalDate.now();

        List<PositionPredictionModel> historicalData = new ArrayList<>();

        for (int i = 3; i >= 0; i--) {
            int month = currentDay.minusMonths(i).getMonthValue();
            int year = currentDay.minusMonths(i).getYear();

            PositionPredictionModel predictionModel =
                    countRecruitmentByPositionIdAndMonth(positionId, month, year);
            historicalData.add(predictionModel);
        }

        return historicalData;
    }

    private PositionPredictionModel countRecruitmentByPositionIdAndMonth(int positionId, int month, int year) {
        return new PositionPredictionModel(month,
                                           recruitmentRepository.countRecruitmentByPositionIdAndMonth(positionId,
                                                                                                      month,
                                                                                                      year));
    }
}
