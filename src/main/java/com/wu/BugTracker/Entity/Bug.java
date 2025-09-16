package com.wu.BugTracker.Entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "bugs")
public class Bug {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bug_seq")
    @SequenceGenerator(name="bug_seq", sequenceName="SEQ_BUGS", allocationSize=1)
    @Column(name = "bug_id")
    private Long bugId;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "reporter_id")
    private Employee reporter;

    @ManyToOne
    @JoinColumn(name = "assign_id")
    private Employee assignee;

    @Column(length = 50)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, length = 20)
    private String priority; // low, medium, high, critical

    @Column(nullable = false, length = 20)
    private String status; // backlog, inprogress, validate, resolved

    @Column(name = "created_at", updatable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "resolved_at")
    private Instant resolvedAt;

    public Bug() {}

    public Bug(Long bugId, Project project, Employee reporter, Employee assignee, String title,
               String description, String priority, String status, Instant createdAt, Instant resolvedAt) {
        this.bugId = bugId;
        this.project = project;
        this.reporter = reporter;
        this.assignee = assignee;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.createdAt = createdAt;
        this.resolvedAt = resolvedAt;
    }

    public Long getBugId() { return bugId; }
    public void setBugId(Long bugId) { this.bugId = bugId; }
    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
    public Employee getReporter() { return reporter; }
    public void setReporter(Employee reporter) { this.reporter = reporter; }
    public Employee getAssignee() { return assignee; }
    public void setAssignee(Employee assignee) { this.assignee = assignee; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public Instant getResolvedAt() { return resolvedAt; }
    public void setResolvedAt(Instant resolvedAt) { this.resolvedAt = resolvedAt; }
}
