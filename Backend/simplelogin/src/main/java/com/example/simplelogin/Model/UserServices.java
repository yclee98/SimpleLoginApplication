package com.example.simplelogin.Model;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.simplelogin.Exception.AuthenticationException;
import com.example.simplelogin.Repository.UserRepositoryInterface;

@Service
public class UserServices implements UserServicesInterface{
    @Autowired
    private final UserRepositoryInterface userRepository;

    public UserServices(UserRepositoryInterface userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
    
    @Override
    public User registerUser(String name, String username, String password, String role) throws AuthenticationException{
        if (name==null || username==null || password == null || role == null){
            throw new AuthenticationException("Empty fields");
        }

        username = username.toLowerCase();

        if(userRepository.findByUsername(username).isPresent()){
            throw new AuthenticationException("Username already exists");
        }
        
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        User u = new User(name, username, hashedPassword, role);
        userRepository.save(u);
        
        return u;
    }

    @Override
    public User validateUser(String username, String password) throws AuthenticationException{
        if (username == null || password == null){
            throw new AuthenticationException("Empty fields");
        }

        username = username.toLowerCase();
        Optional<User> u = userRepository.findByUsername(username);

        if(!u.isPresent() || !BCrypt.checkpw(password, u.get().getPassword())){
            throw new AuthenticationException("Invalid username or password");
        }

        return u.get();
    }

    @Override
    public Void deleteUser(Long id) throws AuthenticationException{
        Optional<User> u = userRepository.findById(id);

        if(!u.isPresent()){
            throw new AuthenticationException("Invalid user");
        }

        userRepository.delete(u.get());
        return null;
    }

    @Override
    public Void deleteUser(String username) throws AuthenticationException {
        Optional<User> u = userRepository.findByUsername(username);

        if(!u.isPresent()){
            throw new AuthenticationException("Invalid user");
        }

        userRepository.delete(u.get());
        return null;
    }

       
}
