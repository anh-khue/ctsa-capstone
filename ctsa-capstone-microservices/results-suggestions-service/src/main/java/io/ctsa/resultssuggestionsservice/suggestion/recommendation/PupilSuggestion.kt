package io.ctsa.resultssuggestionsservice.suggestion.recommendation

import io.ctsa.resultssuggestionsservice.model.centroid.EntranceExamCentroid
import io.ctsa.resultssuggestionsservice.model.centroid.HighSchoolTopCentroid
import io.ctsa.resultssuggestionsservice.model.centroid.MajorCentroid
import io.ctsa.resultssuggestionsservice.model.input.EntranceExamInput
import io.ctsa.resultssuggestionsservice.model.input.HighSchoolTopInput
import io.ctsa.resultssuggestionsservice.model.input.SuggestedMajorInput
import io.ctsa.resultssuggestionsservice.repository.centroid.EntranceExamCentroidRepository
import io.ctsa.resultssuggestionsservice.repository.centroid.HighSchoolTopResultCentroidRepository
import io.ctsa.resultssuggestionsservice.repository.centroid.MajorCentroidRepository
import io.ctsa.resultssuggestionsservice.repository.common.MajorRepository
import org.springframework.stereotype.Component
import java.util.Comparator
import java.util.Objects
import java.util.stream.Collectors

import java.lang.Math.pow
import java.lang.Math.sqrt

@Component
class PupilSuggestion(private val majorCentroidRepository: MajorCentroidRepository,
                      private val highSchoolTopResultCentroidRepository: HighSchoolTopResultCentroidRepository,
                      private val majorRepository: MajorRepository,
                      private val entranceExamCentroidRepository: EntranceExamCentroidRepository) {

    fun suggest(input: SuggestedMajorInput): List<MajorSuggestion> {
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

    private fun score(input: SuggestedMajorInput, centroid: MajorCentroid): Double {
        val highSchoolTopCentroids = highSchoolTopResultCentroidRepository
                .findByMajorCentroidId(centroid.id)

        var squaredDistance = (pow(input.characteristic - centroid.characteristic, 2.0)
                               + pow(input.highSchoolAverage!! - centroid.highSchoolAverage, 2.0)
                               + scoreTopSubjects(input.highSchoolTopInputs, highSchoolTopCentroids))

        if (input.entranceExamInput != null) {
            squaredDistance += scoreEntranceExam(input.entranceExamInput,
                                                 entranceExamCentroidRepository
                                                         .findById(centroid.entranceExamCentroidId)
                                                         .orElse(null))
        }

        return sqrt(squaredDistance)
    }

    private fun scoreTopSubjects(inputs: Collection<HighSchoolTopInput>, centroids: Collection<HighSchoolTopCentroid>): Double {
        var squaredDistance = 0.0

        inputs.forEach { input ->
            centroids.forEach { centroid ->
                if (input.subjectId == centroid.subjectId) {
                    squaredDistance += pow(input.mark * input.weight - centroid.mark * centroid.weight, 2.0)
                }
            }
        }

        return squaredDistance
    }

    private fun scoreEntranceExam(input: EntranceExamInput, centroid: EntranceExamCentroid): Double {
        var squaredDistance = 0.0

        input.entranceExamInputDetails.forEach { inputDetail ->
            centroid.entranceExamCentroidDetails.forEach { centroidDetail ->
                if (inputDetail.entranceExamSubjectId == centroidDetail.entranceExamSubjectId) {
                    squaredDistance += pow(inputDetail.mark - centroidDetail.mark * centroidDetail.weight, 2.0)
                }
            }
        }

        return squaredDistance
    }
}
