package com.example.simplelogin.Model;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) arg0;
        HttpServletResponse httpResponse = (HttpServletResponse) arg1;

        String authHeader = httpRequest.getHeader("Authorization");
        if(authHeader != null){
            String[] authHeaderArr = authHeader.split("Bearer");
            if(authHeaderArr.length > 1 && authHeaderArr[1] != null){
                String token = authHeaderArr[1];
                try{
                    Claims claims = Jwts.parser().setSigningKey(JWTTToken.getAPI_SECRET_KEY())
                        .parseClaimsJws(token).getBody();
                    httpRequest.setAttribute("id", Integer.parseInt(claims.get("id").toString()));

                }catch(Exception e){
                    httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "invalid/expired token");
                    return;
                }
            }else{
                httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be Bearer[token]");
                return;
            }
        }else{
            httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be provided");
            return;
        }
        arg2.doFilter(arg0, arg1);
    }
    
}
