package io.ctsa.careertrendservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoricalSkillDataWrapper {

    private int skillId;
    private List<SkillPredictionModel> historicalData;

    public static Map<Integer, List<SkillPredictionModel>> toMap(List<HistoricalSkillDataWrapper> wrappers) {
        Map<Integer, List<SkillPredictionModel>> map = new HashMap<>();
        wrappers.forEach(wrapper -> map.put(wrapper.skillId, wrapper.historicalData));

        return map;
    }
}