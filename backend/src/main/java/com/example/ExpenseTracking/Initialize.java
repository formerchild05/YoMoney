package com.example.ExpenseTracking;

import com.example.ExpenseTracking.model.entity.Roles;
import com.example.ExpenseTracking.model.entity.User;
import com.example.ExpenseTracking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Initialize implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User u = userRepository.findByUsername("admin");
        if (u == null) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setFullName("admin");
            user.setEmail("admin@admin.com");
            user.setRole(Roles.ADMIN);
            userRepository.save(user);
        }

    }
}
