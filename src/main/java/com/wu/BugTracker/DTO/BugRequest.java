package com.wu.BugTracker.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BugRequest {

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(min = 5, max = 500, message = "Description must be between 5 and 500 characters")
    private String description;

    @NotBlank(message = "Priority is required")
    private String priority; // low, medium, high, critical

    @NotBlank(message = "Status is required")
    private String status; // backlog, inprogress, validate, resolved

    @NotNull(message = "Project ID is required")
    private Long projectId;

    @NotNull(message = "Reporter ID is required")
    private Long reporterId;

    private Long assigneeId; // optional

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }

    public Long getReporterId() { return reporterId; }
    public void setReporterId(Long reporterId) { this.reporterId = reporterId; }

    public Long getAssigneeId() { return assigneeId; }
    public void setAssigneeId(Long assigneeId) { this.assigneeId = assigneeId; }
}

