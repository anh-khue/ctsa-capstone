package io.ctsa.careersservice.controller;

import io.ctsa.careersservice.exception.NotFoundInDatasetException;
import io.ctsa.careersservice.model.Skill;
import io.ctsa.careersservice.service.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    public ResponseEntity insertSkill(@RequestBody Skill skill) {
        try {
            skillService.insertSkill(skill);
            return ResponseEntity.status(OK)
                                 .build();
        } catch (Exception e) {
            return ResponseEntity.status(CONFLICT)
                                 .body("Something wrong happened.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        try {
            return ResponseEntity.status(OK)
                                 .body(skillService.findById(Integer.parseInt(id))
                                                   .orElseThrow(NotFoundInDatasetException::new));
        } catch (NotFoundInDatasetException e) {
            return ResponseEntity.status(NOT_FOUND).build();
        }
    }

    @PostMapping("/list")
    public ResponseEntity getListByIds(@RequestBody List<Integer> ids) {
        return ResponseEntity.status(OK)
                             .body(ids.stream()
                                      .map(skillService::findById)
                                      .collect(Collectors.toList()));
    }
}
