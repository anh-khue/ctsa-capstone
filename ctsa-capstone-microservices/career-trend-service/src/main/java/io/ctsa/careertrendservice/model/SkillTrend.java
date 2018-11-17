package io.ctsa.careertrendservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public
class SkillTrend {

    private Integer skillId;
    private Double predictedNumberOfRecruitment;
}