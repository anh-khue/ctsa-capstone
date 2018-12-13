package io.ctsa.ctsawebapp.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaffRouter {

    @GetMapping("/admin")
    public String adminHome() {
        return "staff/web-admin";
    }

    @GetMapping("/admin/major")
    public String adminMajor() {
        return "staff/major-management";
    }

    @GetMapping("/admin/hr")
    public String adminHR() {
        return "staff/human-resources-management";
    }

    @GetMapping("/admin/spInfo")
    public String adminSpInfo() {
        return "staff/sp-info-management";
    }

    @GetMapping("/admin/keyword")
    public String adminKeyword() {
        return "staff/keyword-management";
    }

    @GetMapping("/admin/high-school")
    public String adminHighSchool() {
        return "staff/high-school-management";
    }

    @GetMapping("/admin/entrance-exam")
    public String adminEntranceExam() {
        return "staff/entrance-exam-management";
    }
}
