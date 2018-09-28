package io.ctsa.basedatasetservice.controller;

import io.ctsa.basedatasetservice.exception.NotFoundInDatasetException;
import io.ctsa.basedatasetservice.model.Skill;
import io.ctsa.basedatasetservice.service.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

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
}
