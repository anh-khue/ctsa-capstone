package io.ctsa.warehouseservice.controller;

import io.ctsa.warehouseservice.model.RequiredSkill;
import io.ctsa.warehouseservice.service.RequiredSkillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

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
        return ResponseEntity.status(OK)
                             .body(requiredSkillService.saveRequiredSkill(requiredSkill));
    }

    @GetMapping("/required_skills/total_pages/top")
    public ResponseEntity findTotalPagesTopSkillsByPositionAndSkillType(
            @RequestParam("position_id") Integer positionId,
            @RequestParam("skill_type_id") Integer skillTypeId,
            @RequestParam("skillsPerPage") Integer skillsPerPage) {
        return ResponseEntity.status(OK)
                             .body(requiredSkillService
                                           .findTotalPagesTopSkillsByPositionAndSkillType(positionId,
                                                                                          skillTypeId,
                                                                                          skillsPerPage));
    }

    @GetMapping("/required_skills/top")
    public ResponseEntity findTopSkillsByPositionAndSkillType(@RequestParam("position_id") Integer positionId,
                                                              @RequestParam("skill_type_id") Integer skillTypeId,
                                                              @RequestParam("page") Integer page,
                                                              @RequestParam("skillsPerPage") Integer skillsPerPage) {
        return ResponseEntity.status(OK)
                             .body(requiredSkillService.findTopSkillsByPositionAndSkillType(positionId, skillTypeId,
                                                                                            page, skillsPerPage));
    }

    @GetMapping("/required_skills/position_id={positionId}/top-skill-types")
    public ResponseEntity findTopSkillsByPosition(@PathVariable String positionId,
                                                  @RequestParam(value = "escape-skill-type-id",
                                                                required = false) Integer escapedSkillTypeId) {
        return ResponseEntity.status(OK)
                             .body(requiredSkillService.findTopSkillTypesByPositionEscape(
                                     Integer.parseInt(positionId),
                                     escapedSkillTypeId)
                             );
    }

    @GetMapping("/required_skills")
    public ResponseEntity getTopSkillsByPosition(@RequestParam("positionId") Integer positionId,
                                                 @RequestParam("top") Integer top) {
        return ResponseEntity.status(OK)
                             .body(requiredSkillService.findTopSkillsByPositionId(positionId, top));
    }

    @GetMapping("/required_skills/history")
    public ResponseEntity getHistoricalData(@RequestParam("positionId") Integer positionId,
                                            @RequestParam("skillId") Integer skillId) {
        return ResponseEntity.status(OK)
                             .body(requiredSkillService.findHistoricalDataByPositionIdAndSkillId(positionId, skillId));
    }
}
