package com.example.simplelogin.Model.JWT;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService2 {
    @Value("${jwt.api.key}")
    private String API_SECRET_KEY;

    public String extractUsername(String jwt){
        return null;
    }

    private Claims extractAllClaims(String token){
        return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(API_SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
