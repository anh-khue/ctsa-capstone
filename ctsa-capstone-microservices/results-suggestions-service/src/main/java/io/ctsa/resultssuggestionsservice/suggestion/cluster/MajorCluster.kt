package io.ctsa.resultssuggestionsservice.suggestion.cluster

import io.ctsa.resultssuggestionsservice.model.EntranceExamResultCentroid
import io.ctsa.resultssuggestionsservice.model.HighSchoolTopResultCentroid
import io.ctsa.resultssuggestionsservice.model.MajorCentroid
import io.ctsa.resultssuggestionsservice.repository.*
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
open class MajorCluster(private val majorResultRepository: MajorResultRepository,
                        private val entranceExamResultRepository: EntranceExamResultRepository,
                        private val highSchoolTopResultRepository: HighSchoolTopResultRepository,
                        private val entranceExamResultCentroidRepository: EntranceExamResultCentroidRepository,
                        private val majorCentroidRepository: MajorCentroidRepository,
                        private val highSchoolTopResultCentroidRepository: HighSchoolTopResultCentroidRepository)
    : Cluster<Int, MajorCentroid> {

    @Transactional
    override fun findCentroid(metricId: Int): MajorCentroid {
        val centroid = MajorCentroid(majorResultRepository.findCentroid(metricId))

        val entranceExamResultCentroid = findEntranceExamResultCentroid(metricId)

        centroid.entranceExamResultCentroid = entranceExamResultCentroid
        centroid.entranceExamResultCentroidId = entranceExamResultCentroid.id
        majorCentroidRepository.saveAndFlush(centroid)

        centroid.highSchoolTopResultCentroids = findHighSchoolTopResultsCentroids(metricId)
        centroid.highSchoolTopResultCentroids.forEach { it.majorCentroidId = centroid.id }
        saveHighSchoolTopResultsCentroids(centroid.highSchoolTopResultCentroids)

        return centroid
    }

    private fun findEntranceExamResultCentroid(majorId: Int): EntranceExamResultCentroid {
        val centroid = EntranceExamResultCentroid(entranceExamResultRepository.findCentroid(majorId))
        entranceExamResultCentroidRepository.saveAndFlush(centroid)
        return centroid
    }

    private fun findHighSchoolTopResultsCentroids(majorId: Int): List<HighSchoolTopResultCentroid> {
        return highSchoolTopResultRepository.findCentroid(majorId)
                .map(::HighSchoolTopResultCentroid)
    }

    private fun saveHighSchoolTopResultsCentroids(centroids: List<HighSchoolTopResultCentroid>) {
        centroids.forEach { highSchoolTopResultCentroidRepository.saveAndFlush(it) }
    }
}