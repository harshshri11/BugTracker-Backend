package com.wu.BugTracker.DTO;

import java.time.Instant;

public class CommentResponse {

    private Long commentId;
    private String body;
    private Long bugId;
    private Long authorId;
    private String authorName;
    private Instant createdAt;

    public Long getCommentId() { return commentId; }
    public void setCommentId(Long commentId) { this.commentId = commentId; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public Long getBugId() { return bugId; }
    public void setBugId(Long bugId) { this.bugId = bugId; }

    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
