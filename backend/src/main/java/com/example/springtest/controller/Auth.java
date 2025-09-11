package com.example.springtest.controller;

import com.example.springtest.model.LoginRequest;
import com.example.springtest.repository.UserRepository;
import com.example.springtest.security.JwtUtil;
import com.example.springtest.service.UserService;
import com.example.springtest.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api")
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
     * instead using jwt, use userId to save localstorage ( jwt maby later ??? )
     * @param loginRequest
     * @return
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

        return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        try {
            userService.saveUser(userDTO);
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
