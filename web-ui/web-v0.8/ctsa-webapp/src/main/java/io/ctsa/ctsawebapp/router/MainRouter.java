package io.ctsa.ctsawebapp.router;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/nghe-nghiep/{id}")
    public String majorDetailAlt(@PathVariable String id,
                                 Model model) {
        model.addAttribute("majorId", id);
        return "major-detail";
    }

    @GetMapping("/thong-tin-tuyen-dung")
    public String jobDetail() {
        return "job-detail";
    }

    @GetMapping("/thong-tin")
    public String summary() {
        return "freshmen/summary";
    }
}
