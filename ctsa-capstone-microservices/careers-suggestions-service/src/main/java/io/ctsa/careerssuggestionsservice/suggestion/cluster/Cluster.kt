package io.ctsa.careerssuggestionsservice.suggestion.cluster

interface Cluster<M, C> {

    fun findCentroid(metricId: M): C
}