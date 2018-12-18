package io.ctsa.careerssuggestionsservice.controller.centroid;

import io.ctsa.careerssuggestionsservice.service.centroid.MajorCentroidService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@CrossOrigin("*")
@RestController
public class MajorCentroidController {

    private final MajorCentroidService majorCentroidService;

    public MajorCentroidController(MajorCentroidService majorCentroidService) {
        this.majorCentroidService = majorCentroidService;
    }

    @GetMapping("/major-centroids/major-id/{majorId}")
    public ResponseEntity getCentroidByMajor(@PathVariable String majorId) {
        return ResponseEntity.status(OK)
                             .body(majorCentroidService.getByMajorId(Integer.parseInt(majorId)));
    }
}
