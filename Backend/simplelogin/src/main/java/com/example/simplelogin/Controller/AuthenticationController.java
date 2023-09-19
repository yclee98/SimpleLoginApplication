package com.example.simplelogin.Controller;

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

import com.example.simplelogin.Model.APIRequestResponse.AuthenticatedResponse;
import com.example.simplelogin.Model.APIRequestResponse.AuthenticationRequestHeader;
import com.example.simplelogin.Model.APIRequestResponse.ErrorResponse;
import com.example.simplelogin.Model.APIRequestResponse.LoginRequest;
import com.example.simplelogin.Model.APIRequestResponse.RegisterRequest;
import com.example.simplelogin.Model.APIRequestResponse.ResponseInterface;
import com.example.simplelogin.Model.Authentication.AuthenticationService;
import com.example.simplelogin.Model.Authentication.AuthenticationServiceInterface;
import com.example.simplelogin.Model.JWT.JWTService;
import com.example.simplelogin.Model.JWT.JWTServiceInterface;
import com.example.simplelogin.Model.User.User;

@RestController
@RequestMapping("/api/authentication")
@CrossOrigin(origins ="${cors.allow}")
public class AuthenticationController {
    @Autowired
    private final AuthenticationServiceInterface authenticationService;

    @Autowired
    private final JWTServiceInterface jwtService;

    public AuthenticationController(AuthenticationService authenticationService,JWTService jwtService){
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseInterface> registerUser(@RequestBody RegisterRequest request){
        String name = request.getName();
        String username = request.getUsername();
        String password = request.getPassword();
        String role = request.getRole();

        try{
            User u = authenticationService.registerUser(name, username, password, role);
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
            User u = authenticationService.validateUser(username, password);        
            ResponseInterface r = new AuthenticatedResponse(u.getName(), u.getUsername(), u.getRole(), jwtService.generateJWTToken(u));
            return new ResponseEntity<>(r, HttpStatus.OK);
        }catch(Exception e){
            ResponseInterface r = new ErrorResponse(e.getMessage());
            return new ResponseEntity<>(r, HttpStatus.UNAUTHORIZED);
        }
        
    }
    // do spring security 
    @GetMapping("/validatetoken")
    public ResponseEntity<String> validateToken(@RequestHeader("authorization") AuthenticationRequestHeader header){
        String authorizationHeader = header.getAuthorization();  
        if(authorizationHeader != null){
            String[] parts = authorizationHeader.split(" ");

            if (parts.length == 2 && parts[0].equalsIgnoreCase("Bearer")) {
                String token = parts[1];

                if(jwtService.validateToken(token)){
                    return ResponseEntity.ok("Authorized");
                }
            }    
        }            
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }
    
    @GetMapping("/validaterole")
    public ResponseEntity<String> validateRole(@RequestHeader("authorization") AuthenticationRequestHeader header){
        String authorizationHeader = header.getAuthorization();       
            
        if(authorizationHeader != null){
            String[] parts = authorizationHeader.split(" ");

            if (parts.length == 2 && parts[0].equalsIgnoreCase("Bearer")) {
                String token = parts[1];

                if(jwtService.hasManagerRole(token)){
                    return ResponseEntity.ok("Authorized");
                }
            }    
        }            
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }
}
