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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.simplelogin.Model.JWTTToken;
import com.example.simplelogin.Model.User;
import com.example.simplelogin.Model.UserServicesInterface;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private final UserServicesInterface userServices;

    public UserController(UserServicesInterface userServices){
        this.userServices = userServices;
    }

    @GetMapping("/allusers")
    public List<User> getUsers(){
        return userServices.getAllUser();
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Map<String, Object> userMap){
        String name = (String) userMap.get("name");
        String username = (String) userMap.get("username");
        String password = (String) userMap.get("password");
        String role = (String) userMap.get("role");
        
        User u = userServices.registerUser(name, username, password, role); 

        return new ResponseEntity<>(JWTTToken.generateJWTToken(u), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userMap){
        String username = (String) userMap.get("username");
        String password = (String) userMap.get("password");
        
        User u = userServices.validateUser(username, password);        

        return new ResponseEntity<>(JWTTToken.generateJWTToken(u), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable long id){     
        userServices.deleteUser(id);

        Map<String, String> map = new HashMap<>();
        map.put("message", "Deleted");
        
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
