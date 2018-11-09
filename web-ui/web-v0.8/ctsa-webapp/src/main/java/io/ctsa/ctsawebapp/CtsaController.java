package io.ctsa.ctsawebapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CtsaController {

    @GetMapping("/trang-chu")
    public String homepage() {
        return "index";
    }

    @GetMapping("/ket-qua")
    public String result() {
        return "result";
    }

    @GetMapping("/thong-tin-tuyen-dung")
    public String jobDetail() {
        return "jobdetail-alternative";
    }

    @GetMapping("/danh-sach-tuyen-dung")
    public String listJob() {
        return "listjob";
    }

    @GetMapping("/thong-tin")
    public String summary() {
        return "summary";
    }
}
