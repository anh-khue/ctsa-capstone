package io.ctsa.ctsawebapp.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainRouter {

    @GetMapping({"/", "/trang-chu"})
    public String homepage() {
        return "index";
    }

    @GetMapping("/ket-qua")
    public String result() {
        return "high-school/result";
    }

    @GetMapping("/thong-tin-tuyen-dung")
    public String jobDetail() {
        return "job-detail";
    }

    @GetMapping("/danh-sach-tuyen-dung")
    public String listJob() {
        return "job-list";
    }

    @GetMapping("/thong-tin")
    public String summary() {
        return "freshmen/summary";
    }
}
