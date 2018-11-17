package io.ctsa.careertrendservice.controller;

import io.ctsa.careertrendservice.model.PositionPredictionModel;
import io.ctsa.careertrendservice.service.PositionTrendService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@CrossOrigin(origins = "*")
@RestController
public class PositionTrendController {

    private final PositionTrendService positionTrendService;

    public PositionTrendController(PositionTrendService positionTrendService) {
        this.positionTrendService = positionTrendService;
    }

    @PostMapping("/position-trend")
    public ResponseEntity getPositionTrendSuggestion(@RequestParam("duration") int duration,
                                                     @RequestParam("positionId") int positionId,
                                                     @RequestBody List<PositionPredictionModel> history) {
        return ResponseEntity.status(OK)
                             .body(positionTrendService.getPositionPrediction(duration,
                                                                              positionId,
                                                                              history));
    }
}