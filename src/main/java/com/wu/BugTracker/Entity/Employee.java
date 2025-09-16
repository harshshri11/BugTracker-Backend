package com.wu.BugTracker.Entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_seq")
    @SequenceGenerator(name="emp_seq", sequenceName="SEQ_EMPLOYEES", allocationSize=1)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 20)
    private String role; // admin, manager, developer, tester

    @Column(length = 100)
    private String team;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt = Instant.now();

    public Employee() {}

    public Employee(Long userId, String username, String email, String password, String role, String team, Instant createdAt) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.team = team;
        this.createdAt = createdAt;
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getTeam() { return team; }
    public void setTeam(String team) { this.team = team; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "Employee{userId=" + userId + ", username='" + username + "', email='" + email +
                "', role='" + role + "', team='" + team + "', createdAt=" + createdAt + "}";
    }
}
