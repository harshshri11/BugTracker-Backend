package com.wu.BugTracker.Entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proj_seq")
    @SequenceGenerator(name="proj_seq", sequenceName="SEQ_PROJECTS", allocationSize=1)
    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "project_name", nullable = false, length = 150)
    private String projectName;

    @Column(length = 500)
    private String description;

    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private Employee manager;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt = Instant.now();

    public Project() {}

    public Project(Long projectId, String projectName, String description, Employee manager, Instant createdAt) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.description = description;
        this.manager = manager;
        this.createdAt = createdAt;
    }

    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }
    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Employee getManager() { return manager; }
    public void setManager(Employee manager) { this.manager = manager; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
