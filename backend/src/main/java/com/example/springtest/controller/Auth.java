package com.example.springtest.controller;

import com.example.springtest.model.LoginRequest;
import com.example.springtest.repository.UserRepository;
import com.example.springtest.security.JwtUtil;
import com.example.springtest.service.UserService;
import com.example.springtest.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RequestMapping("/api/auth")
@RestController
public class Auth {


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * @param loginRequest
     * @return jwt token (JSON format : "token": token)
     * @throws Exception
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken
                        (loginRequest.getUsername(), loginRequest.getPassword()));

        final UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());
        final String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok().body(Map.of("token", token));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        try {
            userService.saveUser(userDTO);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Register sucess"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", "Username or email already exist"));
        }
    }



    @PostMapping("/registerAdmin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> registerAdmin(@RequestBody UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        try {
            userService.saveAdmin(userDTO);
            return ResponseEntity.status(HttpStatus.OK).body("register success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("username or email already exist");
        }
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok().body(new UserDTO(1L, "zxc"));
    }
}
