package com.example.simplelogin.service.imple;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.simplelogin.exception.AuthenticationException;
import com.example.simplelogin.model.User.User;
import com.example.simplelogin.service.JWTServiceInterface;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTService implements JWTServiceInterface{
    @Value("${jwt.api.key}")
    private String API_SECRET_KEY;
    private long TOKEN_VALIDITY = 2*60*60*1000;

    @Override
    public String generateJWTToken(User user) {
        long timestamp = System.currentTimeMillis();
        
        String token = Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, API_SECRET_KEY)
            .setIssuedAt(new Date(timestamp))
            .setExpiration(new Date(timestamp+TOKEN_VALIDITY))
            .claim("id",user.getId())
            .claim("username",user.getUsername())
            .claim("name",user.getName())
            .claim("role",user.getRole())
            .compact();
        return token;
    }  
    
    private Claims decodeToken(String token){
        try{
            Claims claims = Jwts.parser().setSigningKey(API_SECRET_KEY)
                .parseClaimsJws(token).getBody();           
            return claims;            
        }catch(Exception e){
            throw new AuthenticationException("Invalid/expired token");
        }
    }

    @Override
    public boolean validateToken(String token){
        decodeToken(token);
        return true;       
    }

    @Override
    public boolean hasManagerRole(String token) throws AuthenticationException{
        Claims claims = decodeToken(token);        
        String role = claims.get("role", String.class);
        if(role.equalsIgnoreCase("manager")){
            return true;
        }
        throw new AuthenticationException("Unauthorized role");
    }
}
