package com.wu.BugTracker.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProjectRequest {

    @NotBlank(message = "Project name is required")
    private String projectName;

    private String description;

    @NotNull(message = "Manager ID is required")
    private Long managerId;

    // getters & setters
    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getManagerId() { return managerId; }
    public void setManagerId(Long managerId) { this.managerId = managerId; }
}

