package io.ctsa.careertrendservice.service

import io.ctsa.careertrendservice.model.HumanResource
import io.ctsa.careertrendservice.prediction.storage.SmoothingParams
import io.ctsa.careertrendservice.prediction.storage.SmoothingParamsConstants.HUMAN_RESOURCES
import io.ctsa.careertrendservice.prediction.timeseries.ExponentialSmoothingFormula
import io.ctsa.careertrendservice.repository.HumanResourcesRepository
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class HumanResourcesService(private val humanResourcesRepository: HumanResourcesRepository,
                            private val exponentialSmoothingFormula: ExponentialSmoothingFormula,
                            private val smoothingParamsService: SmoothingParamsService) {

    companion object {
        private val log = LoggerFactory.getLogger(HumanResourcesService::class.java)
    }

    val all: List<HumanResource>
        get() = humanResourcesRepository.findAll()

    fun getById(humanResourcesId: Int): Optional<HumanResource> {
        return humanResourcesRepository.findById(humanResourcesId)
    }

    fun getAllByYear(year: Int): List<HumanResource> {
        return humanResourcesRepository.findByYear(year)
    }

    fun predict(predictedYear: Int, majorId: Int): HumanResource {
        val smoothingParams = smoothingParamsService.getSmoothingParams("$HUMAN_RESOURCES-$majorId")

        val nearestPredictionModel = humanResourcesRepository.findFirstByMajorIdOrderByYearDesc(majorId)

        var predictionModel = HumanResource()
        predictionModel.majorId = majorId
        predictionModel.year = predictedYear

        predictionModel = exponentialSmoothingFormula.predict(nearestPredictionModel, predictionModel,
                                                              smoothingParams.alpha, smoothingParams.beta)

        return predictionModel
    }

    fun getLatest(majorId: Int): HumanResource {
        return humanResourcesRepository.findFirstByMajorIdOrderByYearDesc(majorId)
    }

    fun estimateSmoothingParams(majorId: Int): SmoothingParams {
        log.info("Estimating smoothing parameters for Human Resources with major-id = $majorId")
        val humanResources = humanResourcesRepository.findByMajorIdOrderByYearAsc(majorId)

        return smoothingParamsService.findSmoothingParams("$HUMAN_RESOURCES-$majorId",
                                                          exponentialSmoothingFormula,
                                                          humanResources)
    }

    fun getAllByMajorId(majorId: Int): List<HumanResource> {
        return humanResourcesRepository.findByMajorIdOrderByYearAsc(majorId)
    }

    fun predictSeries(endYear: Int, majorId: Int): List<HumanResource> {
        val predictionSeries: MutableList<HumanResource> = mutableListOf()

        val smoothingParams = smoothingParamsService.getSmoothingParams("$HUMAN_RESOURCES-$majorId")

        val nearestPredictionModel = humanResourcesRepository.findFirstByMajorIdOrderByYearDesc(majorId)

        val currentYear = LocalDate.now().year

        for (year in currentYear..endYear) {
            var predictionModel = HumanResource()
            predictionModel.majorId = majorId
            predictionModel.year = year

            predictionModel = exponentialSmoothingFormula.predict(nearestPredictionModel, predictionModel,
                                                                  smoothingParams.alpha, smoothingParams.beta)

            predictionSeries.add(predictionModel)
        }

        return predictionSeries
    }

    fun getAllByMajorIdByPage(majorId: Int, itemPerPage: Int, page: Int): Page<HumanResource> {
        val pageRequest = PageRequest.of(page - 1, itemPerPage)
        return humanResourcesRepository.findByMajorIdOrderByYearAsc(majorId, pageRequest)
    }
}
