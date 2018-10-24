package io.ctsa.careertrendservice.prediction.heuristicsearch;

import org.springframework.stereotype.Component;

@FunctionalInterface
public interface HillClimbingFunction<T> {

    T getBestMove(T currentState);
}
