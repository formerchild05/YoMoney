package com.example.springtest.controller;

import com.example.springtest.model.LoginRequest;
import com.example.springtest.service.UserService;
import com.example.springtest.service.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/LandPage")
@RestController
public class Login {

    @Autowired
    private UserService userService;


    /**
     * instead using jwt, use userId to save localstorage ( jwt maby later ??? )
     * @param loginRequest
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {

        try {
            UserDTO user = userService.Login(loginRequest);
            return ResponseEntity.status(HttpStatus.OK).body(new LoginResponse("logged in successfully", user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
}


    @Getter
    @Setter
    public static class LoginResponse {
        private String message;
        private UserDTO user;

        public LoginResponse(String message, UserDTO u) {
            this.message = message;
            this.user = u;
        }
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok().body(new UserDTO(1, "zxc"));
    }
}
