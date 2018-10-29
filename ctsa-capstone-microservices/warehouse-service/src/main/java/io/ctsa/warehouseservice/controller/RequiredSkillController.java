package io.ctsa.warehouseservice.controller;

import io.ctsa.warehouseservice.model.RequiredSkill;
import io.ctsa.warehouseservice.service.RequiredSkillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@Slf4j
@RestController
public class RequiredSkillController {

    private final RequiredSkillService requiredSkillService;

    public RequiredSkillController(RequiredSkillService requiredSkillService) {
        this.requiredSkillService = requiredSkillService;
    }

    @PostMapping("/required_skills")
    public ResponseEntity save(@RequestBody RequiredSkill requiredSkill) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(requiredSkillService.saveRequiredSkill(requiredSkill));
    }

    @GetMapping("/required_skills/top")
    public ResponseEntity findTopSkillsByPositionAndSkillType(@RequestParam("position_id") Integer positionId,
                                                              @RequestParam("skill_type_id") Integer skillTypeId) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(requiredSkillService.findTopSkillsByPositionAndSkillType(positionId, skillTypeId));
    }

//    @GetMapping("/required_skills/position_id={positionId}/top-skill-types")
//    public ResponseEntity findTopSkillsByPosition(@PathVariable String positionId) {
//        return ResponseEntity.status(HttpStatus.OK)
//                             .body(requiredSkillService.findTopSkillTypesByPosition(Integer.parseInt(positionId)));
//    }

    @GetMapping("/required_skills/position_id={positionId}/top-skill-types")
    public ResponseEntity findTopSkillsByPosition(@PathVariable String positionId,
                                                  @RequestParam(value = "escape-skill-type-id",
                                                                required = false) Integer escapedSkillTypeId) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(requiredSkillService
                                           .findTopSkillTypesByPositionEscape(Integer.parseInt(positionId),
                                                                              escapedSkillTypeId));
    }
}
