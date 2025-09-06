package com.example.springtest.controller;

import com.example.springtest.service.UserService;
import com.example.springtest.service.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/LandPage")
public class Register {

    UserService userService;

    Register(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO)  {
        try {
            userService.Register(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
