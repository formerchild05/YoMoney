package com.example.springtest.service;

import com.example.springtest.model.LoginRequest;
import com.example.springtest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Auth {
    @Autowired
    private DBservice dbservice;




    public boolean login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        User user = dbservice.getUsersByUsername(username);

        if (user == null) {
            return false;
        }
        return password.equals(user.getPassword());
    }
}
