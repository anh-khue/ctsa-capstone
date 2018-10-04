package io.ctsa.warehouseservice.controller;

import io.ctsa.warehouseservice.model.RequiredSkill;
import io.ctsa.warehouseservice.service.RequiredSkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
