package com.example.springtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Testing {
    /** main **/
    @GetMapping("/")
    public String homePage(Model model) {
        return "layout";
    }
    @GetMapping("/page1")
    public String page1(Model model) {
        return "bruhlmao";
    }

    @GetMapping("/submitForm")
    public String handleForm(
            @RequestParam("name") String name,
            @RequestParam("age") int age,
            Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "result";
    }
}
