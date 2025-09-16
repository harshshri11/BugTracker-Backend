package com.wu.BugTracker.model;

public class LoginCreds {
    private String username;
    private String password;

    public LoginCreds() {} // No-arg constructor

    public LoginCreds(String username, String password) { // All-arg constructor
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
