package com.example.simplelogin.service;

import com.example.simplelogin.exception.AuthenticationException;
import com.example.simplelogin.model.User.User;

public interface AuthenticationServiceInterface {
    User validateUser(String username, String password) throws AuthenticationException;
    User registerUser(String name, String username, String password, String role) throws AuthenticationException;
}
