package com.example.simplelogin.Model;

import java.util.List;

import com.example.simplelogin.Exception.AuthenticationException;

public interface UserServicesInterface {
    List<User> getAllUser();
    User validateUser(String username, String password) throws AuthenticationException;
    User registerUser(String name, String username, String password, String role) throws AuthenticationException;
    Void deleteUser(Long id) throws AuthenticationException;
    Void deleteUser(String username) throws AuthenticationException;
}
