package com.wu.BugTracker.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CommentRequest {

    @NotBlank(message = "Comment body is required")
    @Size(min = 2, max = 500, message = "Comment must be between 2 and 500 characters")
    private String body;

    @NotNull(message = "Bug ID is required")
    private Long bugId;

    @NotNull(message = "Author ID is required")
    private Long authorId;

    // Getters and Setters
    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public Long getBugId() { return bugId; }
    public void setBugId(Long bugId) { this.bugId = bugId; }

    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }
}
