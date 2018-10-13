package io.ctsa.resultssuggestionsservice.suggestion.cluster

interface Cluster<M, C> {

    fun findCentroid(metricId: M): C
}