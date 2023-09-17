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

import com.example.simplelogin.APIRequestResponse.AuthenticatedResponse;
import com.example.simplelogin.APIRequestResponse.AuthenticationRequestHeader;
import com.example.simplelogin.APIRequestResponse.ErrorResponse;
import com.example.simplelogin.APIRequestResponse.LoginRequest;
import com.example.simplelogin.APIRequestResponse.RegisterRequest;
import com.example.simplelogin.APIRequestResponse.ResponseInterface;
import com.example.simplelogin.Model.JWT.JWTService;
import com.example.simplelogin.Model.JWT.JWTServiceInterface;
import com.example.simplelogin.Model.User.User;
import com.example.simplelogin.Model.User.UserServicesInterface;

import io.jsonwebtoken.Header;

@RestController
@RequestMapping("/api/authentication")
@CrossOrigin(origins ="${cors.allow}")
public class AuthenticationController {
    @Autowired
    private final UserServicesInterface userServices;

    @Autowired
    private final JWTServiceInterface jwtService;

    public AuthenticationController(UserServicesInterface userServices,JWTService jwtService){
        this.userServices = userServices;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseInterface> registerUser(@RequestBody RegisterRequest request){
        String name = request.getName();
        String username = request.getUsername();
        String password = request.getPassword();
        String role = request.getRole();

        try{
            User u = userServices.registerUser(name, username, password, role);
            ResponseInterface r = new AuthenticatedResponse(u.getName(), u.getUsername(), u.getRole(), jwtService.generateJWTToken(u));
            return new ResponseEntity<>(r, HttpStatus.OK);
        }catch(Exception e){
            ResponseInterface r = new ErrorResponse(e.getMessage());
            return new ResponseEntity<>(r, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseInterface> loginUser(@RequestBody LoginRequest request){
        String username = request.getUsername();
        String password = request.getPassword();

        try{
            User u = userServices.validateUser(username, password);        
            ResponseInterface r = new AuthenticatedResponse(u.getName(), u.getUsername(), u.getRole(), jwtService.generateJWTToken(u));
            return new ResponseEntity<>(r, HttpStatus.OK);
        }catch(Exception e){
            ResponseInterface r = new ErrorResponse(e.getMessage());
            return new ResponseEntity<>(r, HttpStatus.UNAUTHORIZED);
        }
        
    }

    @GetMapping("/validatetoken")
    public ResponseEntity<HttpStatus> validateToken(@RequestHeader("authorization") AuthenticationRequestHeader header){
        String authorizationHeader = header.getAuthorization();  
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
    public ResponseEntity<HttpStatus> validateRole(@RequestHeader("authorization") AuthenticationRequestHeader header){
        String authorizationHeader = header.getAuthorization();       
            
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
