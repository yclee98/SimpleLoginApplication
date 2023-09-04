package com.example.simplelogin.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.simplelogin.Model.JWTService;
import com.example.simplelogin.Model.User.User;
import com.example.simplelogin.Model.User.UserServicesInterface;

@RestController
@RequestMapping("/api/authentication")
@CrossOrigin(origins ="${cors.allow}")
public class AuthenticationController {
    @Autowired
    private final UserServicesInterface userServices;

    @Autowired
    private final JWTService jwtService;

    public AuthenticationController(UserServicesInterface userServices,JWTService jwtService){
        this.userServices = userServices;
        this.jwtService = jwtService;
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

        Map<String, String> response = new HashMap<>();
        response.put("name", u.getName());
        response.put("username", u.getUsername());
        response.put("role", u.getRole());
        response.put("token",jwtService.generateJWTToken(u));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userMap){
        String username = (String) userMap.get("username");
        String password = (String) userMap.get("password");
        
        User u = userServices.validateUser(username, password);        

        Map<String, String> response = new HashMap<>();
        response.put("name", u.getName());
        response.put("username", u.getUsername());
        response.put("role", u.getRole());
        response.put("token",jwtService.generateJWTToken(u));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/validatetoken")
    public ResponseEntity<HttpStatus> validateToken(@RequestHeader Map<String, Object> userMap){
        String authorizationHeader = (String) userMap.get("authorization");  
        if(authorizationHeader != null){
            String[] parts = authorizationHeader.split(" ");

            if (parts.length == 2 && parts[0].equalsIgnoreCase("Bearer")) {
                String token = parts[1];

                if(jwtService.validateToken(token)){
                    return ResponseEntity.ok(HttpStatus.OK);
                }
            }    
        }            
        return ResponseEntity.ok(HttpStatus.UNAUTHORIZED);
    }
    
    @GetMapping("/validaterole")
    public ResponseEntity<HttpStatus> validateRole(@RequestHeader Map<String, Object> userMap){
        String authorizationHeader = (String) userMap.get("authorization");        
            
        if(authorizationHeader != null){
            String[] parts = authorizationHeader.split(" ");

            if (parts.length == 2 && parts[0].equalsIgnoreCase("Bearer")) {
                String token = parts[1];

                if(jwtService.hasManagerRole(token)){
                    return ResponseEntity.ok(HttpStatus.OK);
                }
            }    
        }            
        return ResponseEntity.ok(HttpStatus.UNAUTHORIZED);
    }
}
