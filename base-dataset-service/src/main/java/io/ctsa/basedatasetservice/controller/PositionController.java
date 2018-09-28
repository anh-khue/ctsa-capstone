package io.ctsa.basedatasetservice.controller;

import io.ctsa.basedatasetservice.exception.NotFoundInDatasetException;
import io.ctsa.basedatasetservice.model.Position;
import io.ctsa.basedatasetservice.service.PositionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/positions")
public class PositionController {

    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping
    public ResponseEntity insertPosition(@RequestBody Position position) {
        try {
            positionService.insertPosition(position);
            return ResponseEntity.status(OK)
                                 .build();
        } catch (Exception e) {
            return ResponseEntity.status(CONFLICT)
                                 .body("Sorry, something wrong happened.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        try {
            return ResponseEntity.status(OK)
                                 .body(positionService.findById(Integer.parseInt(id))
                                                      .orElseThrow(NotFoundInDatasetException::new));
        } catch (NotFoundInDatasetException e) {
            return ResponseEntity.status(NOT_FOUND).build();
        }
    }
}