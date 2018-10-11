package io.ctsa.resultssuggestionsservice.suggestion.cluster

import io.ctsa.resultssuggestionsservice.model.EntranceExamResultCentroid
import io.ctsa.resultssuggestionsservice.model.HighSchoolTopResultCentroid
import io.ctsa.resultssuggestionsservice.model.MajorCentroid
import io.ctsa.resultssuggestionsservice.model.MajorResult
import io.ctsa.resultssuggestionsservice.service.EntranceExamResultCentroidService
import io.ctsa.resultssuggestionsservice.service.HighSchoolSubjectService
import io.ctsa.resultssuggestionsservice.service.MajorResultService
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
open class MajorCluster(private val majorResultService: MajorResultService,
                        private val entranceExamResultCentroidService: EntranceExamResultCentroidService,
                        private val highSchoolSubjectService: HighSchoolSubjectService)
    : Cluster<Int, MajorCentroid> {

    @Transactional
    override fun findCentroid(metricId: Int): MajorCentroid {
        val centroid = MajorCentroid()
        centroid.majorId = metricId

        val majorResults = majorResultService.getByMajorId(metricId)

        val entranceExamResultCentroid = findEntranceExamResultCentroid(majorResults)

        centroid.entranceExamResultCentroid = entranceExamResultCentroid
        centroid.entranceExamResultCentroidId = entranceExamResultCentroid.id

        centroid.highSchoolTopResultCentroids = findHighSchoolTopResultsCentroids(majorResults)

        return centroid
    }

    private fun findEntranceExamResultCentroid(majorResults: List<MajorResult>): EntranceExamResultCentroid {
        val centroid = EntranceExamResultCentroid()

        centroid.math = majorResults
                .mapNotNull { it.entranceExamResult }
                .map { it.math }
                .average()

        centroid.literature = majorResults
                .mapNotNull { it.entranceExamResult }
                .map { it.literature }
                .average()

        centroid.english = majorResults
                .mapNotNull { it.entranceExamResult }
                .map { it.english }
                .average()

        centroid.physics = majorResults
                .mapNotNull { it.entranceExamResult }
                .map { it.physics }
                .average()

        centroid.chemistry = majorResults
                .mapNotNull { it.entranceExamResult }
                .map { it.chemistry }
                .average()

        centroid.biology = majorResults
                .mapNotNull { it.entranceExamResult }
                .map { it.biology }
                .average()

        centroid.history = majorResults
                .mapNotNull { it.entranceExamResult }
                .map { it.history }
                .average()

        centroid.geography = majorResults
                .mapNotNull { it.entranceExamResult }
                .map { it.geography }
                .average()

        centroid.id = entranceExamResultCentroidService.save(centroid)

        return centroid
    }

    private fun findHighSchoolTopResultsCentroids(majorResults: List<MajorResult>): List<HighSchoolTopResultCentroid> {
        val allSubjects = highSchoolSubjectService.all

        val centroids = mutableListOf<HighSchoolTopResultCentroid>()

        allSubjects.forEach { subject ->
            val centroid = HighSchoolTopResultCentroid()

            majorResults.forEach { majorResult ->
                val topHighSchoolResults = majorResult.highSchoolTopResults

                centroid.mark = topHighSchoolResults
                        .filter { it.subjectId == subject.id }
                        .map { it.mark }
                        .average()

                centroid.weight = topHighSchoolResults
                        .filter { it.subjectId == subject.id }
                        .mapNotNull { it.weight }
                        .average()
            }

            centroids.add(centroid)
        }

        return centroids
    }
}