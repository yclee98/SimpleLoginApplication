package com.example.simplelogin.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.simplelogin.Model.User;

public interface UserRepositoryInterface extends JpaRepository<User, Long> {
    public Optional<User> findByUsername(String username);
}
