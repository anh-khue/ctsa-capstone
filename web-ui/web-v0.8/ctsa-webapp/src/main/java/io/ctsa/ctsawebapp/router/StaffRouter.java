package io.ctsa.ctsawebapp.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaffRouter {

    @GetMapping("/admin")
    public String adminHome() {
        return "staff/web-admin";
    }

    @GetMapping("/admin/keyword")
    public String adminKeyword() {
        return "staff/keyword-management";
    }
}
