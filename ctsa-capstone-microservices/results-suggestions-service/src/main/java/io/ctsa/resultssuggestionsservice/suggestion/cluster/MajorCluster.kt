package io.ctsa.resultssuggestionsservice.suggestion.cluster

import io.ctsa.resultssuggestionsservice.model.centroid.EntranceExamCentroid
import io.ctsa.resultssuggestionsservice.model.centroid.EntranceExamCentroidDetail
import io.ctsa.resultssuggestionsservice.model.centroid.HighSchoolTopCentroid
import io.ctsa.resultssuggestionsservice.model.centroid.MajorCentroid
import io.ctsa.resultssuggestionsservice.repository.centroid.EntranceExamCentroidDetailRepository
import io.ctsa.resultssuggestionsservice.repository.centroid.EntranceExamCentroidRepository
import io.ctsa.resultssuggestionsservice.repository.centroid.HighSchoolTopResultCentroidRepository
import io.ctsa.resultssuggestionsservice.repository.centroid.MajorCentroidRepository
import io.ctsa.resultssuggestionsservice.repository.common.EntranceExamSubjectRepository
import io.ctsa.resultssuggestionsservice.repository.sample.EntranceExamSampleRepository
import io.ctsa.resultssuggestionsservice.repository.sample.HighSchoolTopSampleRepository
import io.ctsa.resultssuggestionsservice.repository.sample.MajorSampleRepository
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
open class MajorCluster(private val majorSampleRepository: MajorSampleRepository,
                        private val entranceExamSampleRepository: EntranceExamSampleRepository,
                        private val highSchoolTopSampleRepository: HighSchoolTopSampleRepository,
                        private val majorCentroidRepository: MajorCentroidRepository,
                        private val highSchoolTopResultCentroidRepository: HighSchoolTopResultCentroidRepository,
                        private val entranceExamSubjectRepository: EntranceExamSubjectRepository,
                        private val entranceExamCentroidRepository: EntranceExamCentroidRepository,
                        private val entranceExamCentroidDetailRepository: EntranceExamCentroidDetailRepository)
    : Cluster<Int, MajorCentroid> {

    @Transactional
    override fun findCentroid(metricId: Int): MajorCentroid {
        val centroid = MajorCentroid(majorSampleRepository.findCentroid(metricId))

        // Retrieve EntranceExamCentroidId
        val entranceExamCentroid = findEntranceExamCentroid(metricId)
        centroid.entranceExamCentroidId = entranceExamCentroid.id

        // Retrieve generated ID
        majorCentroidRepository.saveAndFlush(centroid)

        centroid.highSchoolTopCentroids = findHighSchoolTopSamplesCentroids(metricId)
        centroid.highSchoolTopCentroids.forEach { it.majorCentroidId = centroid.id }
        saveHighSchoolTopSamplesCentroids(centroid.highSchoolTopCentroids)

        return centroid
    }

    private fun findEntranceExamCentroid(majorId: Int): EntranceExamCentroid {
        val centroid = EntranceExamCentroid()
        // Retrieve generated ID
        entranceExamCentroidRepository.saveAndFlush(centroid)

        entranceExamSubjectRepository.findAll()
                .map { findEntranceExamCentroidDetail(majorId, it.id) }
                .forEach {
                    it.entranceExamCentroidId = centroid.id
                    entranceExamCentroidDetailRepository.saveAndFlush(it)
                }

        return centroid
    }

    private fun findEntranceExamCentroidDetail(majorId: Int, subjectId: Int): EntranceExamCentroidDetail {
        val average = entranceExamSampleRepository.findSubjectAverageByMajor(majorId, subjectId)

        val centroidDetail = EntranceExamCentroidDetail()

        centroidDetail.entranceExamSubjectId = subjectId
        centroidDetail.mark = average

        return centroidDetail
    }

    private fun findHighSchoolTopSamplesCentroids(majorId: Int): List<HighSchoolTopCentroid> {
        return highSchoolTopSampleRepository.findCentroid(majorId)
                .map(::HighSchoolTopCentroid)
    }

    private fun saveHighSchoolTopSamplesCentroids(centroids: Collection<HighSchoolTopCentroid>) {
        centroids.forEach { highSchoolTopResultCentroidRepository.saveAndFlush(it) }
    }
}