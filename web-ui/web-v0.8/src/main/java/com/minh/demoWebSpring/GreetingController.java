package com.minh.demoWebSpring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
    @GetMapping("/trang-chu")
    public String homepage(@RequestParam(name="name", required = false,defaultValue = "World")String name, Model model){
        return "index";
    }
    @GetMapping("/ket-qua")
    public String result(@RequestParam(name="name", required = false,defaultValue = "World")String name, Model model){
        return "result";
    }
    @GetMapping("/thong-tin-tuyen-dung")
    public String jobdetail(@RequestParam(name="name", required = false,defaultValue = "World")String name, Model model){
        return "jobdetail-alternative";
    }
    @GetMapping("/danh-sach-tuyen-dung")
    public String listjob(@RequestParam(name="name", required = false,defaultValue = "World")String name, Model model){
        return "listjob";
    }
    @GetMapping("/thong-tin")
    public String summary(@RequestParam(name="name", required = false,defaultValue = "World")String name, Model model){
        return "summary";
    }


}
