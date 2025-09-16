package com.wu.BugTracker.Mapper;

import com.wu.BugTracker.DTO.CommentRequest;
import com.wu.BugTracker.DTO.CommentResponse;
import com.wu.BugTracker.Entity.Bug;
import com.wu.BugTracker.Entity.Comment;
import com.wu.BugTracker.Entity.Employee;

public class CommentMapper {

    public static Comment toEntity(CommentRequest request, Bug bug, Employee author) {
        Comment comment = new Comment();
        comment.setBody(request.getBody());
        comment.setBug(bug);
        comment.setAuthor(author);
        return comment;
    }

    public static CommentResponse toResponse(Comment comment) {
        CommentResponse response = new CommentResponse();
        response.setCommentId(comment.getCommentId());
        response.setBody(comment.getBody());
        response.setBugId(comment.getBug().getBugId());
        response.setAuthorId(comment.getAuthor().getUserId());
        response.setAuthorName(comment.getAuthor().getUsername());
        response.setCreatedAt(comment.getCreatedAt());
        return response;
    }
}

