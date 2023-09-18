package com.example.simplelogin.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.simplelogin.Model.User.User;
import com.example.simplelogin.Model.User.UserServiceInterface;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private final UserServiceInterface userServices;

    public UserController(UserServiceInterface userServices){
        this.userServices = userServices;
    }

    @GetMapping("/allusers")
    public List<User> getUsers(){
        return userServices.getAllUser();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable long id){     
        userServices.deleteUser(id);

        Map<String, String> map = new HashMap<>();
        map.put("message", "Deleted");
        
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
