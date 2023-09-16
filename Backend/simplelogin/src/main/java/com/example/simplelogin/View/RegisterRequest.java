package com.example.simplelogin.View;

import com.example.simplelogin.Model.User.Role;

public class RegisterRequest {
    private String name;
    private String username;
    private String password;
    private String role;

    public RegisterRequest(String name, String username, String password, String role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role.toUpperCase();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Role getRole() {
        if(role.equals(Role.MANAGER.name())){
            return Role.MANAGER;
        }else if(role.equals(Role.USER.name())){
            return Role.USER;
        }else{
            return null;
        }
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    

}
