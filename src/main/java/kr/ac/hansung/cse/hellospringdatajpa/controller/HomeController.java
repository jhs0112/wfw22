package kr.ac.hansung.cse.hellospringdatajpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String redirectToProducts() {
        return "redirect:/products";
    }

    @RequestMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }
}
