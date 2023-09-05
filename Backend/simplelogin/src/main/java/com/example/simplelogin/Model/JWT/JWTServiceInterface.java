package com.example.simplelogin.Model.JWT;

import com.example.simplelogin.Exception.AuthenticationException;
import com.example.simplelogin.Model.User.User;

public interface JWTServiceInterface {
    public String generateJWTToken(User user);
    public boolean validateToken(String token);
    public boolean hasManagerRole(String token) throws AuthenticationException; 
}
