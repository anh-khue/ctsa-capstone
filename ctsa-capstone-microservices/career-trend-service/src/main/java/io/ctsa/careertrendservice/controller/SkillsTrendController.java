package io.ctsa.careertrendservice.controller;

import io.ctsa.careertrendservice.model.HistoricalSkillDataWrapper;
import io.ctsa.careertrendservice.model.SkillPredictionModel;
import io.ctsa.careertrendservice.service.SkillsTrendService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@CrossOrigin(origins = "*")
@RestController
public class SkillsTrendController {

    private final SkillsTrendService skillsTrendService;

    public SkillsTrendController(SkillsTrendService skillsTrendService) {
        this.skillsTrendService = skillsTrendService;
    }

    @PostMapping("/skills-trend")
    public ResponseEntity getSkillsTrendSuggestion(@RequestParam("duration") int duration,
                                                   @RequestBody List<HistoricalSkillDataWrapper> wrappers) {
        Map<Integer, List<SkillPredictionModel>> map = HistoricalSkillDataWrapper.toMap(wrappers);

        return ResponseEntity.status(OK)
                             .body(skillsTrendService.getSkillTrends(duration, map));
    }

}