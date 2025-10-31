package com.example.ExpenseTracking.service;

import com.example.ExpenseTracking.model.entity.Roles;
import com.example.ExpenseTracking.model.entity.User;
import com.example.ExpenseTracking.repository.UserRepository;
import com.example.ExpenseTracking.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().toString()));

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), authorities);

    }
    public String findNameByUsername(String username) {
        return userRepository.findByUsername(username).getFullName();
    }

    public User findUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    public User saveUser(UserDTO user) {
        user.setRole(Roles.USER);
        return userRepository.save(user.mapUserDTOToUser());
    }

    public User saveAdmin(UserDTO user) {
        user.setRole(Roles.ADMIN);
        return userRepository.save(user.mapUserDTOToUser());
    }

    public UserDTO deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        if (user != null) {
            UserDTO u = new UserDTO().mapUserToUserDTO(user);
            userRepository.delete(user);
            return u;
        }
        return null;
    }
}
