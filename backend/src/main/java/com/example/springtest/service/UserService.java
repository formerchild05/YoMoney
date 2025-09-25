package com.example.springtest.service;

import com.example.springtest.model.LoginRequest;
import com.example.springtest.model.entity.Roles;
import com.example.springtest.model.entity.User;
import com.example.springtest.repository.UserRepository;
import com.example.springtest.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
           throw new UsernameNotFoundException(username);
        }
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole().toString()));

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), authorities);

    }

    public User findUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    public User mapUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setFullName(userDTO.getFullName());
        user.setRole(userDTO.getRole());
        return user;
    }

    public User saveUser(UserDTO user) {
        user.setRole(Roles.USER);
        return userRepository.save(mapUserDTOToUser(user));
    }

    public User saveAdmin(UserDTO user) {
        user.setRole(Roles.ADMIN);
        return userRepository.save(mapUserDTOToUser(user));
    }
//    public void updateUser(UserDTO userDTO) {
//        User user = new User();
//        user.setEmail(userDTO.getEmail());
//        user.setFullName(userDTO.getFullName());
//        user.setPassword(userDTO.getPassword());
//        user.setUsername(userDTO.getUsername());
//        userRepository.save(user);
//    }
//
//    public UserDTO getUserById(int userId) {
//        UserDTO userDTO = new UserDTO();
//        User u = userRepository.findByUserId(userId);
//        userDTO.setFullName(u.getFullName());
//        userDTO.setEmail(u.getEmail());
//        userDTO.setUsername(u.getUsername());
//        return userDTO;
//    }
//
//    public UserDTO Login(LoginRequest loginRequest) throws Exception {
//        User user = userRepository.findByUsername(loginRequest.getUsername());
//
//        if(user == null || !user.getPassword().equals(loginRequest.getPassword())) {
//            throw new Exception("User not found or incorrect password");
//        }
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUserId(user.getUserId());
//        userDTO.setUsername(user.getUsername());
//        return userDTO;
//    }
//
//    public void Register(UserDTO userDTO) throws Exception {
//        User u = userRepository.findByUsername(userDTO.getUsername());
//        if (u != null) {
//            throw new Exception("Username already exists");
//        } else {
//            User user = new User();
//            user.setFullName(userDTO.getFullName());
//            user.setEmail(userDTO.getEmail());
//            user.setPassword(userDTO.getPassword());
//            user.setUsername(userDTO.getUsername());
//            userRepository.save(user);
//        }
//
//
//    }


}
