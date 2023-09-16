package com.example.simplelogin.Model.JWT;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.subst.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService2 {
    @Value("${jwt.api.key}")
    private String API_SECRET_KEY;
    private long TOKEN_VALIDITY = 2*60*60*1000;

    public String generatToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
        Map<String, Object> extractClaim,
        UserDetails userDetails
    ){
        long timestamp = System.currentTimeMillis();
        return Jwts
            .builder()
            .setClaims(extractClaim)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(timestamp))
            .setExpiration(new Date(timestamp+TOKEN_VALIDITY))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    
    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    private Claims extractAllClaims(String token){
        return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
    
    public boolean isTokenValid(String token, UserDetails userDetails){
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(API_SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    
}
