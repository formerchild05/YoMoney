package com.example.ExpenseTracking.repository;

import com.example.ExpenseTracking.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

        User findByUsername(String username);


//        User delete(User user);
}