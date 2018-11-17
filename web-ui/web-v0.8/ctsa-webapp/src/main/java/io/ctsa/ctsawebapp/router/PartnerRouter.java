package io.ctsa.ctsawebapp.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PartnerRouter {

    @GetMapping("/tin-tuyen-dung")
    public String listJob() {
        return "partner/job-list";
    }
}
