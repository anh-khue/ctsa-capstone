package io.ctsa.careertrendservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public
class PositionTrend {

    private Integer positionId;
    private Double predictedNumberOfRecruitment;
}