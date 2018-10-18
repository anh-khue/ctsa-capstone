package io.ctsa.resultssuggestionsservice.suggestion.recommendation;

import io.ctsa.resultssuggestionsservice.model.*;
import io.ctsa.resultssuggestionsservice.repository.EntranceExamResultCentroidRepository;
import io.ctsa.resultssuggestionsservice.repository.HighSchoolTopResultCentroidRepository;
import io.ctsa.resultssuggestionsservice.repository.MajorCentroidRepository;
import io.ctsa.resultssuggestionsservice.repository.MajorRepository;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@Component
public class PupilSuggestion {

    private final MajorCentroidRepository majorCentroidRepository;

    private final HighSchoolTopResultCentroidRepository highSchoolTopResultCentroidRepository;

    private final MajorRepository majorRepository;

    private final EntranceExamResultCentroidRepository entranceExamResultCentroidRepository;

    public PupilSuggestion(MajorCentroidRepository majorCentroidRepository,
                           HighSchoolTopResultCentroidRepository highSchoolTopResultCentroidRepository,
                           MajorRepository majorRepository,
                           EntranceExamResultCentroidRepository entranceExamResultCentroidRepository) {
        this.majorCentroidRepository = majorCentroidRepository;
        this.highSchoolTopResultCentroidRepository = highSchoolTopResultCentroidRepository;
        this.majorRepository = majorRepository;
        this.entranceExamResultCentroidRepository = entranceExamResultCentroidRepository;
    }

    public List<MajorSuggestion> suggest(SuggestedMajor input) {

        return majorCentroidRepository.findAll()
                                      .stream()
                                      .map(majorCentroid -> {
                                          MajorSuggestion suggestion = new MajorSuggestion();
                                          suggestion.setMajor(majorRepository.findById(majorCentroid.getMajorId())
                                                                             .orElse(null));
                                          suggestion.setDistance(score(input, majorCentroid));
                                          return suggestion;
                                      })
                                      .sorted(Comparator.comparingDouble(MajorSuggestion::getDistance))
                                      .collect(Collectors.toList());
    }

    private double score(SuggestedMajor input, MajorCentroid centroid) {
        List<HighSchoolTopResultCentroid> highSchoolTopResultCentroids = highSchoolTopResultCentroidRepository
                .findByMajorCentroidId(centroid.getMajorId());

        double squaredDistance = pow(input.getCharacteristic() - centroid.getCharacteristic(), 2)
                + pow(input.getHighSchoolAverage() - centroid.getHighSchoolAverage(), 2)
                + scoreTopSubjects(input.getHighSchoolTopInputs(), highSchoolTopResultCentroids);

        if (input.getEntranceExamInput() != null) {
            squaredDistance += scoreEntranceExam(input.getEntranceExamInput(),
                                                 Objects.requireNonNull(
                                                         entranceExamResultCentroidRepository
                                                                 .findById(centroid.getEntranceExamResultCentroidId())
                                                                 .orElse(null)));
        }

        return sqrt(squaredDistance);
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

    private double scoreEntranceExam(EntranceExamInput input, EntranceExamResultCentroid centroid) {
        double squaredDistance = pow(input.getMath() - centroid.getMath(), 2)
                + pow(input.getLiterature() - centroid.getLiterature(), 2)
                + pow(input.getEnglish() - centroid.getEnglish(), 2);

        squaredDistance += input.getPhysics() != null && centroid.getPhysics() != null ?
                           pow(input.getPhysics() - centroid.getPhysics(), 2) : 0;

        squaredDistance += input.getChemistry() != null && centroid.getChemistry() != null ?
                           pow(input.getChemistry() - centroid.getChemistry(), 2) : 0;

        squaredDistance += input.getBiology() != null && centroid.getBiology() != null ?
                           pow(input.getBiology() - centroid.getBiology(), 2) : 0;

        squaredDistance += input.getHistory() != null && centroid.getHistory() != null ?
                           pow(input.getHistory() - centroid.getHistory(), 2) : 0;

        squaredDistance += input.getGeography() != null && centroid.getGeography() != null ?
                           pow(input.getGeography() - centroid.getGeography(), 2) : 0;

        return squaredDistance;
    }
}
