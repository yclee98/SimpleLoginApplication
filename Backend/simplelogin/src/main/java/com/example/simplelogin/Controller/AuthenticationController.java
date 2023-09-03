package com.example.simplelogin.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.simplelogin.Model.JWTTToken;
import com.example.simplelogin.Model.User.User;
import com.example.simplelogin.Model.User.UserServicesInterface;

@RestController
@RequestMapping("/api/authentication")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {
    @Autowired
    private final UserServicesInterface userServices;

    public AuthenticationController(UserServicesInterface userServices){
        this.userServices = userServices;
    }

    @GetMapping("/")
    public String indexPage(){
        return "welcome";
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
}
