package com.example.springtest.service;

import com.example.springtest.model.User;
import com.example.springtest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBservice {
    @Autowired
    private UserRepository userRepository;

    public DBservice(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean createUser(User user) {
        if (user.getUsername().equals(userRepository.findByUsername(user.getUsername()))) {
            return false;
        } else {
            userRepository.save(user);
            return true;
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUsersByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
