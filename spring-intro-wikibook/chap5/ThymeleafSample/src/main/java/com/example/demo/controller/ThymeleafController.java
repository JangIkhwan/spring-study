package com.example.demo.controller;

import com.example.demo.entity.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {
    @GetMapping("show")
    public String showView(Model model){
        Member member = new Member(1, "회원01");
        model.addAttribute("mb", member);
        model.addAttribute("name", "이순신");
        return "useThymeleaf";
    }
}
