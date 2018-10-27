package io.ctsa.careertrendservice.prediction.heuristicsearch;

@FunctionalInterface
public interface HillClimbingFunction<T> {

    T getBestMove(T currentState);
}
