package io.ctsa.careertrendservice.prediction.heuristicsearch.function;

@FunctionalInterface
public interface HillClimbingFunction<T> {

    T getBestMove(T currentState);
}
