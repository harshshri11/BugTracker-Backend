package com.wu.BugTracker.DTO;

public class EmployeeResponse {

    private Long id;
    private String username;
    private String email;
    private String role;
    private String team;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getTeam() { return team; }
    public void setTeam(String team) { this.team = team; }
}

