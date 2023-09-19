package com.example.simplelogin.model.APIRequestResponse;

public class AuthenticatedResponse implements ResponseInterface{
    private String name;
    private String username;
    private String role;
    private String token;
    public AuthenticatedResponse(String name, String username, String role, String token) {
        this.name = name;
        this.username = username;
        this.role = role;
        this.token = token;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    
}
