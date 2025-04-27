package com.example.springtest.controller;

import com.example.springtest.model.User;
import com.example.springtest.repository.UserRepository;
import com.example.springtest.service.DBservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Register {
    @Autowired
    DBservice dbservice;

    @GetMapping("/register")
    public String register(Model model) {
        return "registerUI";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        if (dbservice.createUser(user)) {
            model.addAttribute("status", "success");
            return "registerUI";
        } else {
            model.addAttribute("status", "Username already exists");
            return "registerUI";
        }
    }

}
