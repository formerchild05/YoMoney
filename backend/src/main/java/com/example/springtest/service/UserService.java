package com.example.springtest.service;

import com.example.springtest.model.LoginRequest;
import com.example.springtest.model.entity.User;
import com.example.springtest.repository.UserRepository;
import com.example.springtest.service.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    UserRepository userRepository;

    /**
     * inject depen
     * @param userRepository
     */
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void updateUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setFullName(userDTO.getFullName());
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        userRepository.save(user);
    }

    public UserDTO getUserById(int userId) {
        UserDTO userDTO = new UserDTO();
        User u = userRepository.findByUserId(userId);
        userDTO.setFullName(u.getFullName());
        userDTO.setEmail(u.getEmail());
        userDTO.setUsername(u.getUsername());
        return userDTO;
    }

    public UserDTO Login(LoginRequest loginRequest) throws Exception {
        User user = userRepository.findByUsername(loginRequest.getUsername());

        if(user == null || !user.getPassword().equals(loginRequest.getPassword())) {
            throw new Exception("User not found or incorrect password");
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }

    public void Register(UserDTO userDTO) throws Exception {
        User u = userRepository.findByUsername(userDTO.getUsername());
        if (u != null) {
            throw new Exception("Username already exists");
        } else {
            User user = new User();
            user.setFullName(userDTO.getFullName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setUsername(userDTO.getUsername());
            userRepository.save(user);
        }


    }


}
