package com.example.simplelogin.service;

import java.util.List;
import java.util.Optional;

import com.example.simplelogin.exception.AuthenticationException;
import com.example.simplelogin.model.User.User;

public interface UserServiceInterface {
    List<User> getAllUser();
    Optional<User> getUserByUsername(String username);
    void registerUser(User user);
    boolean ifUserExists(String username); 
    void deleteUser(Long id) throws AuthenticationException;
    void deleteUser(String username) throws AuthenticationException;
}
