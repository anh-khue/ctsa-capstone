package io.ctsa.careerssuggestionsservice.suggestion.cluster

import io.ctsa.careerssuggestionsservice.model.centroid.EntranceExamCentroidDetail
import io.ctsa.careerssuggestionsservice.model.centroid.HighSchoolTopCentroid
import io.ctsa.careerssuggestionsservice.model.centroid.MajorCentroid
import io.ctsa.careerssuggestionsservice.repository.centroid.EntranceExamCentroidDetailRepository
import io.ctsa.careerssuggestionsservice.repository.centroid.HighSchoolTopCentroidRepository
import io.ctsa.careerssuggestionsservice.repository.centroid.MajorCentroidRepository
import io.ctsa.careerssuggestionsservice.repository.common.EntranceExamSubjectRepository
import io.ctsa.careerssuggestionsservice.repository.sample.EntranceExamSampleRepository
import io.ctsa.careerssuggestionsservice.repository.sample.HighSchoolTopSampleRepository
import io.ctsa.careerssuggestionsservice.repository.sample.MajorSampleRepository
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
open class MajorCluster(private val majorSampleRepository: MajorSampleRepository,
                        private val entranceExamSampleRepository: EntranceExamSampleRepository,
                        private val highSchoolTopSampleRepository: HighSchoolTopSampleRepository,
                        private val majorCentroidRepository: MajorCentroidRepository,
                        private val highSchoolTopCentroidRepository: HighSchoolTopCentroidRepository,
                        private val entranceExamSubjectRepository: EntranceExamSubjectRepository,
                        private val entranceExamCentroidDetailRepository: EntranceExamCentroidDetailRepository)
    : Cluster<Int, MajorCentroid> {

    @Transactional
    override fun findCentroid(metricId: Int): MajorCentroid {
        val centroid = MajorCentroid(majorSampleRepository.findCentroid(metricId))

        // Retrieve generated ID
        majorCentroidRepository.saveAndFlush(centroid)

        // Retrieve EntranceExamCentroidId
        val entranceExamCentroidDetails = findEntranceExamCentroid(metricId)
                .map {
                    it.majorCentroidId = centroid.id
                    entranceExamCentroidDetailRepository.saveAndFlush(it)
                }
        centroid.entranceExamCentroidDetails = entranceExamCentroidDetails


        val highSchoolTopCentroids = findHighSchoolTopSamplesCentroids(metricId)
                .map {
                    it.majorCentroidId = centroid.id
                    highSchoolTopCentroidRepository.saveAndFlush(it)
                }
        centroid.highSchoolTopCentroids = highSchoolTopCentroids
        majorCentroidRepository.saveAndFlush(centroid)

        return centroid
    }

    private fun findEntranceExamCentroid(majorId: Int): List<EntranceExamCentroidDetail> {
        return entranceExamSubjectRepository.findAll()
                .map { findEntranceExamCentroidDetail(majorId, it.id) }
                .toList()
    }

    private fun findEntranceExamCentroidDetail(majorId: Int,
                                               subjectId: Int): EntranceExamCentroidDetail {
        val average = entranceExamSampleRepository.findSubjectAverageByMajor(majorId, subjectId)

        val centroidDetail = EntranceExamCentroidDetail()

        centroidDetail.entranceExamSubjectId = subjectId
        centroidDetail.mark = average

        return centroidDetail
    }

    private fun findHighSchoolTopSamplesCentroids(majorId: Int): List<HighSchoolTopCentroid> {
        return highSchoolTopSampleRepository.findCentroid(majorId)
                .map(::HighSchoolTopCentroid)
                .toList()
    }
}