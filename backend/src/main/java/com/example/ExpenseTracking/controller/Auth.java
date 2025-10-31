package com.example.ExpenseTracking.controller;

import com.example.ExpenseTracking.model.LoginRequest;
import com.example.ExpenseTracking.security.JwtUtil;
import com.example.ExpenseTracking.service.UserService;
import com.example.ExpenseTracking.service.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RequestMapping("/api/auth")
@RestController
public class Auth {


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

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

        log.info("user login success with token: {}", token);
        return ResponseEntity.ok().body(Map.of("token", token));

        } catch (Exception e) {
            log.error("user {} login fail: {}", loginRequest.getUsername(), e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        try {
            userService.saveUser(userDTO);

            log.info("user register with : {}" + userDTO.getUsername());

            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Register sucess"));
        } catch (Exception e) {
            log.error("user {} register fail: {}", userDTO, e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("error", "Username or email already exist"));
        }
    }


    /**
     * no log yet
     * @param userDTO
     * @return
     */
    @PostMapping("/registerAdmin")
    @PreAuthorize("hasRole('ADMIN')")
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

    @GetMapping("/Aauth")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> Aauth() {
        return ResponseEntity.ok().body("admin");
    }

    @GetMapping("/Uauth")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> Uauth() {
        return ResponseEntity.ok().body("user");
    }
}
