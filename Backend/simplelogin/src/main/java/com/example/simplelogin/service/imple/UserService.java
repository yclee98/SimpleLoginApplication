package com.example.simplelogin.service.imple;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.simplelogin.exception.AuthenticationException;
import com.example.simplelogin.model.User.User;
import com.example.simplelogin.repository.UserRepositoryInterface;
import com.example.simplelogin.service.UserServiceInterface;

@Service
public class UserService implements UserServiceInterface{
    @Autowired
    private final UserRepositoryInterface userRepository;

    public UserService(UserRepositoryInterface userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }  

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void registerUser(User user) {
        userRepository.save(user);        
    }

    @Override
    public boolean ifUserExists(String username) {
        return userRepository.existsByUsername(username);
    }     

    @Override
    public void deleteUser(Long id) throws AuthenticationException{
        Optional<User> u = userRepository.findById(id);

        if(!u.isPresent()){
            throw new AuthenticationException("Invalid user");
        }

        userRepository.delete(u.get());
    }

    @Override
    public void deleteUser(String username) throws AuthenticationException {
        Optional<User> u = userRepository.findByUsername(username);

        if(!u.isPresent()){
            throw new AuthenticationException("Invalid user");
        }

        userRepository.delete(u.get());
    }
}
