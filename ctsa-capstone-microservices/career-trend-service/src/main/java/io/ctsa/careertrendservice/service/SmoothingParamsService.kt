package io.ctsa.careertrendservice.service

import io.ctsa.careertrendservice.prediction.PredictionModel
import io.ctsa.careertrendservice.prediction.heuristicsearch.SmoothingAlphaOptimization
import io.ctsa.careertrendservice.prediction.heuristicsearch.SmoothingBetaOptimization
import io.ctsa.careertrendservice.prediction.storage.SmoothingParams
import io.ctsa.careertrendservice.prediction.storage.SmoothingParamsRepository
import io.ctsa.careertrendservice.prediction.timeseries.ExponentialSmoothingFormula
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SmoothingParamsService(private val smoothingParamsRepository: SmoothingParamsRepository) {

    companion object {
        private const val defaultAlpha: Double = 0.789
        private const val defaultBeta: Double = 0.789

        private val log = LoggerFactory.getLogger(SmoothingParamsService::class.java)
    }

    private fun estimateSmoothingParams(key: String,
                                        exponentialSmoothingFormula: ExponentialSmoothingFormula,
                                        predictionModels: List<PredictionModel>): SmoothingParams {
        val params = getSmoothingParams(key)
        log.info("Current alpha: ${params.alpha} - beta: ${params.beta}")

        // Estimate Alpha
        val smoothingAlphaOptimization = SmoothingAlphaOptimization(exponentialSmoothingFormula,
                                                                    predictionModels,
                                                                    params.beta)
        log.info("Estimating alpha")
        val estimatedAlpha = smoothingAlphaOptimization.getBestMove(params.alpha)
        log.info("Estimated alpha is $estimatedAlpha")

        // Estimate Beta
        val smoothingBetaOptimization = SmoothingBetaOptimization(exponentialSmoothingFormula,
                                                                  predictionModels,
                                                                  estimatedAlpha)
        log.info("Estimating beta")
        val estimatedBeta = smoothingBetaOptimization.getBestMove(params.beta)
        log.info("Estimated beta is $estimatedBeta")

        return SmoothingParams(estimatedAlpha, estimatedBeta)
    }

    fun findSmoothingParams(key: String,
                            exponentialSmoothingFormula: ExponentialSmoothingFormula,
                            predictionModels: List<PredictionModel>): SmoothingParams {
        val params = estimateSmoothingParams(key, exponentialSmoothingFormula, predictionModels)

        if (smoothingParamsRepository.getParam(key) != null) {
            smoothingParamsRepository.updateParam(key, params)
        } else {
            smoothingParamsRepository.addParam(key, params)
        }

        return params
    }

    fun getSmoothingParams(key: String): SmoothingParams {
        return if (smoothingParamsRepository.getParam(key) != null) {
            smoothingParamsRepository.getParam(key)
        } else {
            SmoothingParams(defaultAlpha, defaultBeta)
        }
    }
}