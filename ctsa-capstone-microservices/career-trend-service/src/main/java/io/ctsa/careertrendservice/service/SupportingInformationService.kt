package io.ctsa.careertrendservice.service

import io.ctsa.careertrendservice.model.SupportingInformation
import io.ctsa.careertrendservice.prediction.storage.SmoothingParams
import io.ctsa.careertrendservice.prediction.timeseries.ExponentialSmoothingFormula
import io.ctsa.careertrendservice.repository.SupportingInformationRepository
import org.springframework.stereotype.Service
import java.util.Optional

import io.ctsa.careertrendservice.prediction.storage.SmoothingParamsConstants.*
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import java.time.LocalDate

@Service
class SupportingInformationService(private val supportingInformationRepository: SupportingInformationRepository,
                                   private val exponentialSmoothingFormula: ExponentialSmoothingFormula,
                                   private val smoothingParamsService: SmoothingParamsService) {

    companion object {
        private val log = LoggerFactory.getLogger(SupportingInformationService::class.java)
    }

    val all: List<SupportingInformation>
        get() = supportingInformationRepository.findAll()

    fun getById(supportingInformationId: Int): Optional<SupportingInformation> {
        return supportingInformationRepository.findById(supportingInformationId)
    }

    fun getAllByYear(year: Int): List<SupportingInformation> {
        return supportingInformationRepository.findByYear(year)
    }

    fun predict(predictedYear: Int, majorId: Int): SupportingInformation {
        val smoothingParams = smoothingParamsService
                .getSmoothingParams("$SUPPORTING_INFORMATION-$majorId")

        val nearestPredictionModel = supportingInformationRepository
                .findFirstByMajorIdOrderByYearDesc(majorId)

        var predictionModel = SupportingInformation()
        predictionModel.majorId = majorId
        predictionModel.year = predictedYear

        predictionModel = exponentialSmoothingFormula.predict(nearestPredictionModel, predictionModel,
                                                              smoothingParams.alpha, smoothingParams.beta)
        predictionModel.unit = nearestPredictionModel.unit

        return predictionModel
    }

    fun getLatest(majorId: Int): SupportingInformation {
        return supportingInformationRepository.findFirstByMajorIdOrderByYearDesc(majorId)
    }

    fun estimateSmoothingParams(majorId: Int): SmoothingParams {
        log.info("Estimating smoothing parameters for Supporting Information with major-id = $majorId")
        val supportingInformationList = supportingInformationRepository
                .findByMajorIdOrderByYearAsc(majorId)

        return smoothingParamsService.findSmoothingParams("$SUPPORTING_INFORMATION-$majorId",
                                                          exponentialSmoothingFormula,
                                                          supportingInformationList)
    }

    fun getAllByMajorId(majorId: Int): List<SupportingInformation> {
        return supportingInformationRepository.findByMajorIdOrderByYearAsc(majorId)
    }

    fun predictSeries(endYear: Int, majorId: Int): List<SupportingInformation> {
        val predictionSeries: MutableList<SupportingInformation> = mutableListOf()

        val smoothingParams = smoothingParamsService.getSmoothingParams("$HUMAN_RESOURCES-$majorId")

        val nearestPredictionModel = supportingInformationRepository
                .findFirstByMajorIdOrderByYearDesc(majorId)

        val currentYear = LocalDate.now().year

        for (year in currentYear..endYear) {
            var predictionModel = SupportingInformation()
            predictionModel.majorId = majorId
            predictionModel.year = year
            predictionModel.unit = nearestPredictionModel.unit

            predictionModel = exponentialSmoothingFormula.predict(nearestPredictionModel, predictionModel,
                                                                  smoothingParams.alpha, smoothingParams.beta)

            predictionSeries.add(predictionModel)
        }

        return predictionSeries
    }

    fun getAllByMajorIdByPage(majorId: Int, itemPerPage: Int, page: Int): Page<SupportingInformation> {
        val pageRequest = PageRequest.of(page - 1, itemPerPage)
        return supportingInformationRepository.findByMajorIdOrderByYearAsc(majorId, pageRequest)
    }
}
