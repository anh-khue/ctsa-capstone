package io.ctsa.careerssuggestionsservice.suggestion.recommendation

import io.ctsa.careerssuggestionsservice.model.centroid.EntranceExamCentroidDetail
import io.ctsa.careerssuggestionsservice.model.centroid.HighSchoolTopCentroid
import io.ctsa.careerssuggestionsservice.model.centroid.MajorCentroid
import io.ctsa.careerssuggestionsservice.suggestion.recommendation.input.HighSchoolResult
import io.ctsa.careerssuggestionsservice.suggestion.recommendation.input.AcademicResult
import io.ctsa.careerssuggestionsservice.repository.centroid.HighSchoolTopCentroidRepository
import io.ctsa.careerssuggestionsservice.repository.centroid.MajorCentroidRepository
import io.ctsa.careerssuggestionsservice.repository.common.MajorRepository
import io.ctsa.careerssuggestionsservice.suggestion.recommendation.input.EntranceExamResult
import org.springframework.stereotype.Component

import java.lang.Math.pow
import java.lang.Math.sqrt

@Component
class PupilSuggestion(private val majorCentroidRepository: MajorCentroidRepository,
                      private val highSchoolTopCentroidRepository: HighSchoolTopCentroidRepository,
                      private val majorRepository: MajorRepository) {

    fun suggest(input: AcademicResult): List<MajorSuggestion> {
        return majorCentroidRepository.findAll()
                .asSequence()
                .map { majorCentroid ->
                    val suggestion = MajorSuggestion()

                    suggestion.major = majorRepository
                            .findById(majorCentroid.majorId)
                            .orElse(null)
                    suggestion.distance = score(input, majorCentroid)

                    suggestion
                }
                .sortedBy { it.distance }
                .toList()
    }

    private fun score(input: AcademicResult, centroid: MajorCentroid): Double {
        val highSchoolTopCentroids = highSchoolTopCentroidRepository
                .findByMajorCentroidId(centroid.id)

        var squaredDistance = (pow(input.characteristic - centroid.characteristic, 2.0)
                               + pow(input.highSchoolAverage!! - centroid.highSchoolAverage, 2.0)
                               + scoreTopSubjects(input.highSchoolResults, highSchoolTopCentroids))

        if (input.entranceExamResults != null) {
//            squaredDistance += scoreEntranceExam(input.entranceExamResults,
//                                                 centroid.entranceExamCentroidDetails)
            squaredDistance = scoreEntranceExam(input.entranceExamResults, centroid.entranceExamCentroidDetails)
        }

        return sqrt(squaredDistance)
    }

    private fun scoreTopSubjects(inputs: Collection<HighSchoolResult>, centroids: Collection<HighSchoolTopCentroid>): Double {
        var squaredDistance = 0.0

        inputs.forEach { input ->
            centroids.forEach { centroid ->
                squaredDistance += if (input.subjectId == centroid.subjectId) {
                    pow((input.mark - centroid.mark) * centroid.weight, 2.0)
                } else {
//                    pow(centroid.mark * centroid.weight, 2.0)
//                    pow(10 * centroid.weight, 2.0)
                    0.0
                }
            }
        }

        return squaredDistance
    }

    private fun scoreEntranceExam(entranceExamResults: MutableCollection<EntranceExamResult>,
                                  entranceExamCentroidDetails: MutableCollection<EntranceExamCentroidDetail>)
            : Double {
        var squaredDistance = 0.0

        entranceExamResults.forEach { inputDetail ->
            entranceExamCentroidDetails.forEach { centroidDetail ->
                squaredDistance += if (inputDetail.entranceExamSubjectId == centroidDetail.entranceExamSubjectId) {
                    pow((inputDetail.mark - centroidDetail.mark) * centroidDetail.weight, 2.0)
                } else {
//                    pow(centroidDetail.mark * centroidDetail.weight, 2.0)
//                    pow(10 * centroidDetail.weight, 2.0)
                    0.0
                }
            }
        }

        return squaredDistance
    }
}
