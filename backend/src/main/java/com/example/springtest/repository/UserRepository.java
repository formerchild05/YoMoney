package com.example.springtest.repository;

import com.example.springtest.model.entity.User;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserId(Integer userId);

    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findByCreatedAt(LocalDateTime createdAt, Sort sort);

    void delete(User user);


    /**
     * save() -> co id thi = update, nguoc lai la insert
     * @param user
     * @return
     */
    User save(User user);
}