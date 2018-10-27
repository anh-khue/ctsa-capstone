package io.ctsa.careertrendservice.service

import io.ctsa.careertrendservice.model.Salary
import io.ctsa.careertrendservice.prediction.storage.SmoothingParams
import io.ctsa.careertrendservice.prediction.storage.SmoothingParamsConstants.*
import io.ctsa.careertrendservice.prediction.timeseries.ExponentialSmoothingFormula
import io.ctsa.careertrendservice.repository.SalaryRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class SalaryService(private val salaryRepository: SalaryRepository,
                    private val exponentialSmoothingFormula: ExponentialSmoothingFormula,
                    private val smoothingParamsService: SmoothingParamsService) {

    companion object {
        private val log = LoggerFactory.getLogger(SalaryService::class.java)
    }

    val all: List<Salary>
        get() = salaryRepository.findAll()

    fun getById(salaryId: Int): Optional<Salary> {
        return salaryRepository.findById(salaryId)
    }

    fun getAllByYear(year: Int): List<Salary> {
        return salaryRepository.findByYear(year)
    }

    fun predict(predictedYear: Int, majorId: Int): Salary {
        val smoothingParams = smoothingParamsService.getSmoothingParams("$SALARY-$majorId")

        val nearestPredictionModel = salaryRepository.findFirstByMajorIdOrderByYearDesc(majorId)

        var predictionModel = Salary()
        predictionModel.majorId = majorId
        predictionModel.year = predictedYear

        predictionModel = exponentialSmoothingFormula.predict(nearestPredictionModel, predictionModel,
                                                              smoothingParams.alpha, smoothingParams.beta)

        return predictionModel
    }

    fun getLatest(majorId: Int): Salary {
        return salaryRepository.findFirstByMajorIdOrderByYearDesc(majorId)
    }

    fun estimateSmoothingParams(majorId: Int): SmoothingParams {
        log.info("Estimating smoothing parameters for Salary with major-id = $majorId")
        val salaries = salaryRepository.findByMajorIdOrderByYearAsc(majorId)

        return smoothingParamsService.findSmoothingParams("$SALARY-$majorId",
                                                          exponentialSmoothingFormula,
                                                          salaries)
    }
}

