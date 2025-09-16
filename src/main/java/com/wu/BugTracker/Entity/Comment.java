package com.wu.BugTracker.Entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq")
    @SequenceGenerator(name="comment_seq", sequenceName="SEQ_COMMENTS", allocationSize=1)
    @Column(name = "comment_id")
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "bug_id", nullable = false)
    private Bug bug;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Employee author;

    @Column(length = 500)
    private String body;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt = Instant.now();

    public Comment() {}

    public Comment(Long commentId, Bug bug, Employee author, String body, Instant createdAt) {
        this.commentId = commentId;
        this.bug = bug;
        this.author = author;
        this.body = body;
        this.createdAt = createdAt;
    }

    public Long getCommentId() { return commentId; }
    public void setCommentId(Long commentId) { this.commentId = commentId; }
    public Bug getBug() { return bug; }
    public void setBug(Bug bug) { this.bug = bug; }
    public Employee getAuthor() { return author; }
    public void setAuthor(Employee author) { this.author = author; }
    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
