package io.ctsa.resultssuggestionsservice.suggestion.similarity

interface Similarity<T> {

    fun score(item1: T, item2: T): Double
}
