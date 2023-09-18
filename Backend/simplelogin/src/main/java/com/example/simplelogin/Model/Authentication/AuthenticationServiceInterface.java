package com.example.simplelogin.Model.Authentication;

import com.example.simplelogin.Exception.AuthenticationException;
import com.example.simplelogin.Model.User.User;

public interface AuthenticationServiceInterface {
    User validateUser(String username, String password) throws AuthenticationException;
    User registerUser(String name, String username, String password, String role) throws AuthenticationException;
}
