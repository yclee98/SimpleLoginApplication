package com.example.simplelogin.Model.Authentication;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.simplelogin.Exception.AuthenticationException;
import com.example.simplelogin.Model.User.User;
import com.example.simplelogin.Model.User.UserService;

@Service
public class AuthenticationService implements AuthenticationServiceInterface{
    @Autowired
    private final UserService userServices;
    
    public AuthenticationService(UserService userServices) {
        this.userServices = userServices;
    }

    @Override
    public User registerUser(String name, String username, String password, String role) throws AuthenticationException{
        if (name==null || username==null || password == null || role == null){
            throw new AuthenticationException("Empty fields");
        }

        if (name=="" || username=="" || password == "" || role == ""){
            throw new AuthenticationException("Empty fields");
        }

        username = username.toLowerCase();

        if(userServices.ifUserExists(username)){
            throw new AuthenticationException("Username already exists");
        }
        
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        User u = new User(name, username, hashedPassword, role);
        userServices.registerUser(u);
        
        return u;
    }

    @Override
    public User validateUser(String username, String password) throws AuthenticationException{
        if (username == null || password == null){
            throw new AuthenticationException("Empty fields");
        }

        if (username=="" || password == ""){
            throw new AuthenticationException("Empty fields");
        }

        username = username.toLowerCase();
        Optional<User> u = userServices.getUserByUsername(username);

        if(!u.isPresent() || !BCrypt.checkpw(password, u.get().getPassword())){
            throw new AuthenticationException("Invalid username or password");
        }

        return u.get();
    }
    
}
