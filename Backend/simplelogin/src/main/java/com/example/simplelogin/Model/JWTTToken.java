package com.example.simplelogin.Model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTTToken {
    
    private static final String API_SECRET_KEY = "apikey";
    private static final long TOKEN_VALIDITY = 2*60*60*1000;
    
    public static Map<String, String> generateJWTToken(User user) {
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
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }   

    public static String getAPI_SECRET_KEY(){
        return API_SECRET_KEY;
    }
}
