package io.ctsa.resultssuggestionsservice.suggestion.cluster

import io.ctsa.resultssuggestionsservice.model.EntranceExamResultCentroid
import io.ctsa.resultssuggestionsservice.model.HighSchoolTopResultCentroid
import io.ctsa.resultssuggestionsservice.model.MajorCentroid
import io.ctsa.resultssuggestionsservice.repository.EntranceExamResultRepository
import io.ctsa.resultssuggestionsservice.repository.HighSchoolTopResultRepository
import org.springframework.stereotype.Component

@Component
class MajorCluster(private val entranceExamResultRepository: EntranceExamResultRepository,
                   private val highSchoolTopResultRepository: HighSchoolTopResultRepository)
    : Cluster<Int, MajorCentroid> {

    override fun findCentroid(metricId: Int): MajorCentroid {
        val centroid = MajorCentroid()
        centroid.majorId = metricId

        val entranceExamResultCentroid = findEntranceExamResultCentroid(metricId)

        centroid.entranceExamResultCentroid = entranceExamResultCentroid
        centroid.entranceExamResultCentroidId = entranceExamResultCentroid.id

        centroid.highSchoolTopResultCentroids = findHighSchoolTopResultsCentroids(metricId)

        return centroid
    }

    private fun findEntranceExamResultCentroid(majorId: Int): EntranceExamResultCentroid {
        val entranceExamResult = entranceExamResultRepository.findCentroid(majorId)
        return EntranceExamResultCentroid(entranceExamResult)
    }

    private fun findHighSchoolTopResultsCentroids(majorId: Int): List<HighSchoolTopResultCentroid> {
        return highSchoolTopResultRepository.findCentroid(majorId)
                .map(::HighSchoolTopResultCentroid)
    }
}