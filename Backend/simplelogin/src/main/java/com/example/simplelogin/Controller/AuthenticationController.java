package com.example.simplelogin.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.simplelogin.Model.JWT.JWTService;
import com.example.simplelogin.Model.JWT.JWTService2;
import com.example.simplelogin.Model.JWT.JWTServiceInterface;
import com.example.simplelogin.Model.User.Role;
import com.example.simplelogin.Model.User.User;
import com.example.simplelogin.Model.User.UserServicesInterface;
import com.example.simplelogin.View.AuthenticationResponse;
import com.example.simplelogin.View.ErrorResponse;
import com.example.simplelogin.View.LoginRequest;
import com.example.simplelogin.View.RegisterRequest;
import com.example.simplelogin.View.RequestResponseInterface;

@RestController
@RequestMapping("/api/authentication")
@CrossOrigin(origins ="${cors.allow}")
public class AuthenticationController {
    @Autowired
    private final UserServicesInterface userServices;

    @Autowired
    private final JWTServiceInterface jwtService;

    @Autowired
    private final JWTService2 jwtService2;

    @Autowired
    private final AuthenticationManager authenticationManager;

    public AuthenticationController(UserServicesInterface userServices, JWTServiceInterface jwtService,
            JWTService2 jwtService2, AuthenticationManager authenticationManager) {
        this.userServices = userServices;
        this.jwtService = jwtService;
        this.jwtService2 = jwtService2;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register2")
    public ResponseEntity<RequestResponseInterface> registerUser2(@RequestBody RegisterRequest request){
        try{
            User user = userServices.registerUser(
                request.getName(), 
                request.getUsername(),
                request.getPassword(),
                request.getRole());
            String jwtToken = jwtService2.generatToken(user);
            return new ResponseEntity<>(new AuthenticationResponse(jwtToken), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Map<String, Object> userMap){
        String name = (String) userMap.get("name");
        String username = (String) userMap.get("username");
        String password = (String) userMap.get("password");
        String role = (String) userMap.get("role");
        Map<String, String> response = new HashMap<>();

        try{
            User u = userServices.registerUser(name, username, password, Role.MANAGER);        
            response.put("name", u.getName());
            response.put("username", u.getUsername());
            response.put("role", u.getRole());
            response.put("token",jwtService.generateJWTToken(u));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
    
    @PostMapping("/login2")
    public ResponseEntity<RequestResponseInterface> loginUser2(@RequestBody LoginRequest request){
        try{
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            User user = userServices.getUserByUsername(request.getUsername());
            String jwtToken = jwtService2.generatToken(user);
            return new ResponseEntity<>(new AuthenticationResponse(jwtToken), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userMap){
        String username = (String) userMap.get("username");
        String password = (String) userMap.get("password");
        Map<String, String> response = new HashMap<>();

        try{
            User u = userServices.validateUser(username, password);        
            response.put("name", u.getName());
            response.put("username", u.getUsername());
            response.put("role", u.getRole());
            response.put("token",jwtService.generateJWTToken(u));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        
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
