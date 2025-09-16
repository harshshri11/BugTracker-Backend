package com.wu.BugTracker.Controller;

import com.wu.BugTracker.DTO.CommentRequest;
import com.wu.BugTracker.Entity.Comment;
import com.wu.BugTracker.Service.CommentService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // Create Comment
//    @PostMapping
//    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
//        Comment saved = commentService.createComment(comment);
//        return ResponseEntity.ok(saved);
//    }
    
    @PostMapping
    public ResponseEntity<Comment> createComment(@Valid @RequestBody CommentRequest request) {
        Comment saved = commentService.createComment(request);
        return ResponseEntity.ok(saved);
    }

    // Get Comment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        Comment comment = commentService.getCommentById(id);
        return ResponseEntity.ok(comment);
    }

    // Get All Comments
    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }

    // Update Comment
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment updated) {
        Comment saved = commentService.updateComment(id, updated);
        return ResponseEntity.ok(saved);
    }

    // Delete Comment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
