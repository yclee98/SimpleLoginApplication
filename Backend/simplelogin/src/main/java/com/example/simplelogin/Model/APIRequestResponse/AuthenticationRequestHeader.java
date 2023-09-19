package com.example.simplelogin.model.APIRequestResponse;

public class AuthenticationRequestHeader {
    private String authorization;

    public AuthenticationRequestHeader(String authorization) {
        this.authorization = authorization;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

}
