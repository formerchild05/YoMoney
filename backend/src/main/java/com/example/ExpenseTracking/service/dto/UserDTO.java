package com.example.ExpenseTracking.service.dto;

import com.example.ExpenseTracking.model.entity.Roles;
import com.example.ExpenseTracking.model.entity.User;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long userId;
    private String username;
    private String email;
    private String fullName;
    private String password;
    private Roles role;

    public UserDTO(String username, String email, String fullName, String password) {
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }

    public UserDTO(String username, String fullName, String email) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
    }

    public UserDTO(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public UserDTO mapUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO(user.getUserId(), user.getUsername());
        userDTO.setFullName(user.getFullName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    public User mapUserDTOToUser() {
        User user = new User();
        user.setUsername(this.getUsername());
        user.setFullName(this.getFullName());
        user.setEmail(this.getEmail());
        user.setPassword(this.getPassword());
        user.setRole(this.getRole());
        return user;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
