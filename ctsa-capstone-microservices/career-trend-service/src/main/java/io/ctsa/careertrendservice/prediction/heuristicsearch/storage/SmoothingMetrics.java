package io.ctsa.careertrendservice.prediction.heuristicsearch.storage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmoothingMetrics {

    private double alpha;
    private double beta;
    private double meanSquaredError;
}
