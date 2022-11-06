package com.example.werkplekkenfrontend.models;

public class CurrentUser {

    private String id;
    private String JWTtoken;

    public CurrentUser() {}

    public String getId() {
        return id;
    }

    public String getJWTtoken() {
        return JWTtoken;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setJWTtoken(String JWTtoken) {
        this.JWTtoken = JWTtoken;
    }
}
