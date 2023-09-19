package com.example.simplelogin.service;

import com.example.simplelogin.exception.AuthenticationException;
import com.example.simplelogin.model.User.User;

public interface JWTServiceInterface {
    public String generateJWTToken(User user);
    public boolean validateToken(String token);
    public boolean hasManagerRole(String token) throws AuthenticationException; 
}
