package io.ctsa.careersservice.controller;

import io.ctsa.careersservice.model.SkillType;
import io.ctsa.careersservice.service.SkillTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@CrossOrigin(origins = "*")
@RestController
public class SkillTypeController {

    private final SkillTypeService skillTypeService;

    public SkillTypeController(SkillTypeService skillTypeService) {
        this.skillTypeService = skillTypeService;
    }

    @GetMapping("/skill-types")
    public ResponseEntity findByBusinessFieldId(@RequestParam("business-field-id") Integer businessFieldId) {
        List<SkillType> skillTypes = skillTypeService.findByBusinessFieldId(businessFieldId);

        if (skillTypes.isEmpty()) {
            return ResponseEntity.status(NO_CONTENT)
                                 .build();
        }

        return ResponseEntity.status(OK)
                             .body(skillTypeService.findByBusinessFieldId(businessFieldId));
    }
}
