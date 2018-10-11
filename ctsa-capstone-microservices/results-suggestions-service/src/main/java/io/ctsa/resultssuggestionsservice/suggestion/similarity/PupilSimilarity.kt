package io.ctsa.resultssuggestionsservice.suggestion.similarity

import io.ctsa.resultssuggestionsservice.model.MajorResult
import org.springframework.stereotype.Service
import java.lang.Math.pow

@Service
open class PupilSimilarity : EuclideanDistance<MajorResult> {

    override fun score(item1: MajorResult, item2: MajorResult): Double {
//        return 1 / (1 + sumSquaredDistance(item1, item2))
        return 0.0
    }

    /*protected fun sumSquaredDistance(result1: MajorResult, result2: MajorResult): Double {
        var squaredDistance = pow(result1.characteristic.toDouble() - result2.characteristic.toDouble(), 2.0)

        squaredDistance += scoreHighSchoolResults(result1.highSchoolResults, result2.highSchoolResults)
        squaredDistance += scoreHobbies(result1.hobbies, result2.hobbies)

        return squaredDistance
    }

    private fun scoreHighSchoolResults(topSubjects1: List<HighSchoolTopResult>,
                                       topSubjects2: List<HighSchoolTopResult>): Double {
        var squaredDistance = 0.0

        topSubjects1.forEach { sbj1 ->
            topSubjects2.forEach {
                if (sbj1.subjectId == it.subjectId)
                    squaredDistance += pow(sbj1.mark * sbj1.weight - it.mark * it.weight, 2.0)
            }
        }

        return squaredDistance
    }

    private fun scoreHobbies(hobbies1: List<SuggestedResultWithHobby>,
                             hobbies2: List<SuggestedResultWithHobby>): Double {
        var squaredDistance = 0.0

        for (h1 in hobbies1) {
            for (h2 in hobbies2) {
                if (h1.hobbyId == h2.hobbyId)
                    squaredDistance += pow((h1.weight - h2.weight).toDouble(), 2.0)
            }
        }

        return squaredDistance;
    }*/
}
