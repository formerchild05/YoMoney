package com.example.ExpenseTracking.controller;

import com.example.ExpenseTracking.model.entity.Roles;
import com.example.ExpenseTracking.service.UserService;
import com.example.ExpenseTracking.service.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/account")
public class Account {

    @Autowired
    private UserService userService;

    @GetMapping("/details")
    public ResponseEntity<?> getUserdetails(Authentication authentication) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(authentication.getName());
        List<Roles> roles = authentication.getAuthorities().stream()
                .map(auth ->
                        Roles.valueOf(auth.getAuthority().replace("ROLE_", "")))
                .toList();

        userDTO.setRole(roles.get(0));
        userDTO.setFullName(userService.findNameByUsername(authentication.getName()));

        log.info("getUserdetails : {}", userDTO);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if(userService.deleteUser(id) == null) {
            log.info("deleteUser but not found : {}", id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "user not found"));
        }
        log.info("deleteUser : {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "User deleted"));

    }
}
