package io.ctsa.resultssuggestionsservice.suggestion.recommendation;

import io.ctsa.resultssuggestionsservice.model.HighSchoolTopInput;
import io.ctsa.resultssuggestionsservice.model.HighSchoolTopResultCentroid;
import io.ctsa.resultssuggestionsservice.model.MajorCentroid;
import io.ctsa.resultssuggestionsservice.model.SuggestedMajor;
import io.ctsa.resultssuggestionsservice.repository.HighSchoolTopResultCentroidRepository;
import io.ctsa.resultssuggestionsservice.repository.MajorCentroidRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.*;

@Component
public class PupilSuggestion {

    private final MajorCentroidRepository majorCentroidRepository;

    private final HighSchoolTopResultCentroidRepository highSchoolTopResultCentroidRepository;

    public PupilSuggestion(MajorCentroidRepository majorCentroidRepository, HighSchoolTopResultCentroidRepository highSchoolTopResultCentroidRepository) {
        this.majorCentroidRepository = majorCentroidRepository;
        this.highSchoolTopResultCentroidRepository = highSchoolTopResultCentroidRepository;
    }

    public Integer suggest(SuggestedMajor input) {
        List<MajorCentroid> centroids = majorCentroidRepository.findAll();

        Map<MajorCentroid, Double> scoreMap = new HashMap<>();
        for (MajorCentroid centroid : centroids) {
            double score = score(input, centroid);
            scoreMap.put(centroid, score);
        }

        Map.Entry<MajorCentroid, Double> min = null;
        for (Map.Entry<MajorCentroid, Double> entry : scoreMap.entrySet()) {
            if (min == null || min.getValue() > entry.getValue()) {
                min = entry;
            }
        }

        return min != null ? min.getKey().getMajorId() : null;
    }

    private double score(SuggestedMajor input, MajorCentroid centroid) {
        List<HighSchoolTopResultCentroid> highSchoolTopResultCentroids = highSchoolTopResultCentroidRepository
                .findByMajorCentroidId(centroid.getMajorId());

        return sqrt(pow(input.getCharacteristic() - centroid.getCharacteristic(), 2)
                            + pow(input.getHighSchoolAverage() - centroid.getHighSchoolAverage(), 2)
                            + scoreTopSubjects(input.getHighSchoolTopInputs(), highSchoolTopResultCentroids));
    }

    private double scoreTopSubjects(List<HighSchoolTopInput> inputs, List<HighSchoolTopResultCentroid> centroids) {
        double squaredDistance = 0.0;

        for (HighSchoolTopInput input : inputs) {
            for (HighSchoolTopResultCentroid centroid : centroids) {
                if (input.getSubjectId() == centroid.getSubjectId()) {
                    squaredDistance += pow(input.getMark() * input.getWeight() - centroid.getMark() * centroid.getWeight(), 2);
                }
            }
        }

        return squaredDistance;
    }
}
