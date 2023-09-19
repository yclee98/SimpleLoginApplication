package com.example.simplelogin.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.simplelogin.model.User.User;

public interface UserRepositoryInterface extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
}
