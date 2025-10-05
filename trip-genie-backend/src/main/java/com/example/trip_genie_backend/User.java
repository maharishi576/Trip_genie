package com.example.trip_genie_backend;

public class User {
    private String email;
    private String mobile;
    private String name;
    
    public User() {}
    
    public User(String email, String mobile, String name) {
        this.email = email;
        this.mobile = mobile;
        this.name = name;
    }
    
    // Getters and Setters
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}