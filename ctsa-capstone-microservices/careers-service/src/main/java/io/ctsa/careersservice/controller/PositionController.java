package io.ctsa.careersservice.controller;

import io.ctsa.careersservice.exception.NotFoundInDatasetException;
import io.ctsa.careersservice.model.Position;
import io.ctsa.careersservice.service.PositionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@CrossOrigin(origins = "*")
@RestController
public class PositionController {

    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping("/positions")
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

    @GetMapping("/positions/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        try {
            return ResponseEntity.status(OK)
                                 .body(positionService.findById(Integer.parseInt(id))
                                                      .orElseThrow(NotFoundInDatasetException::new));
        } catch (NotFoundInDatasetException e) {
            return ResponseEntity.status(NOT_FOUND).build();
        }
    }

    @GetMapping("/positions")
    public ResponseEntity getAll() {
        return ResponseEntity.status(OK)
                             .body(positionService.findAll());
    }
}