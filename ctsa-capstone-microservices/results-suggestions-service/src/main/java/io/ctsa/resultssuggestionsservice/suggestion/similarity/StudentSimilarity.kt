package io.ctsa.resultssuggestionsservice.suggestion.similarity

import org.springframework.stereotype.Service
import java.lang.Math.pow

@Service
class StudentSimilarity : PupilSimilarity() {

    companion object {
        const val PHYSICS_CHEMISTRY = 2
        const val CHEMISTRY_BIOLOGY = 2
        const val PHYSICS_BIOLOGY = 3
    }

    override fun score(item1: SuggestedResult, item2: SuggestedResult): Double {
        var squareDistance = sumSquaredDistance(item1, item2)

        squareDistance += scoreEntranceExamResults(item1.entranceExamResult, item2.entranceExamResult)

        return 1 / (1 + squareDistance)
    }

    private fun scoreEntranceExamResults(exam1: EntranceExamResult, exam2: EntranceExamResult): Double {
        val squareDistance = pow(exam1.math - exam2.math, 2.0)
        +pow(exam1.english - exam2.english, 2.0)
        +pow(exam1.literature - exam2.literature, 2.0)
        +scoreEntranceExamResults(exam1, exam2)

        return squareDistance
    }

    private fun scoreNaturalScienceSubjectsResults(exam1: EntranceExamResult, exam2: EntranceExamResult): Double {
        var squareDistance = 0.0

        if (exam1.physics != null) {
            squareDistance += when {
                exam2.physics != null -> pow(exam1.physics - exam2.physics, 2.0)
                exam2.chemistry != null -> pow(exam1.physics - exam2.chemistry * PHYSICS_CHEMISTRY, 2.0)
                exam2.biology != null -> pow(exam1.physics - exam2.biology * PHYSICS_BIOLOGY, 2.0)
                else -> 0.0
            }
        }

        if (exam1.chemistry != null) {
            squareDistance += when {
                exam2.chemistry != null -> pow(exam1.chemistry - exam2.chemistry, 2.0)
                exam2.physics != null -> pow(exam1.chemistry - exam2.physics * PHYSICS_CHEMISTRY, 2.0)
                exam2.biology != null -> pow(exam1.chemistry - exam2.biology * CHEMISTRY_BIOLOGY, 2.0)
                else -> 0.0
            }
        }

        if (exam1.biology != null) {
            squareDistance += when {
                exam2.biology != null -> pow(exam1.biology - exam2.biology, 2.0)
                exam2.physics != null -> pow(exam1.biology - exam2.physics * PHYSICS_BIOLOGY, 2.0)
                exam2.chemistry != null -> pow(exam1.biology - exam2.chemistry * CHEMISTRY_BIOLOGY, 2.0)
                else -> 0.0
            }
        }

        return squareDistance
    }
}