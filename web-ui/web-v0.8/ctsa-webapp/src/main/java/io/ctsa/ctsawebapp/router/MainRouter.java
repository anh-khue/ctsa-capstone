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

    @GetMapping("/tuyen-dung/{id}")
    public String job(@PathVariable String id,
                      Model model) {
        model.addAttribute("recruitmentId", id);
        return "recruitment-detail";
    }

    @GetMapping("/thong-tin-tuyen-dung")
    public String jobDetail() {
        return "job-detail";
    }

    @GetMapping("/thong-tin")
    public String summary() {
        return "freshmen/summary";
    }

    @GetMapping("/sign-in")
    public String signIn() {
        return "sign-in";
    }
}
