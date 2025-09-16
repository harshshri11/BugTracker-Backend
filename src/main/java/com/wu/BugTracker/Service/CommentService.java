package com.wu.BugTracker.Service;

import com.wu.BugTracker.DTO.CommentRequest;
import com.wu.BugTracker.Entity.Bug;
import com.wu.BugTracker.Entity.Comment;
import com.wu.BugTracker.Entity.Employee;
import com.wu.BugTracker.Exception.NotFoundException;
import com.wu.BugTracker.Repository.BugRepository;
import com.wu.BugTracker.Repository.CommentRepository;
import com.wu.BugTracker.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BugRepository bugRepository;
    private final EmployeeRepository employeeRepository;

    public CommentService(CommentRepository commentRepository,
                          BugRepository bugRepository,
                          EmployeeRepository employeeRepository) {
        this.commentRepository = commentRepository;
        this.bugRepository = bugRepository;
        this.employeeRepository = employeeRepository;
    }

    // Create Comment
    public Comment createComment(CommentRequest request) {
        Bug bug = bugRepository.findById(request.getBugId())
                .orElseThrow(() -> new NotFoundException("Bug not found with id: " + request.getBugId()));

        Employee author = employeeRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new NotFoundException("Author not found with id: " + request.getAuthorId()));

        Comment comment = new Comment();
        comment.setBody(request.getBody());
        comment.setBug(bug);
        comment.setAuthor(author);

        return commentRepository.save(comment);
    }

    // Get Comment by ID
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comment not found with id: " + id));
    }

    // Get All Comments
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // Update Comment
    public Comment updateComment(Long id, Comment updated) {
        Comment existing = getCommentById(id);
        existing.setBody(updated.getBody());
        return commentRepository.save(existing);
    }

    // Delete Comment
    public void deleteComment(Long id) {
        Comment existing = getCommentById(id);
        commentRepository.delete(existing);
    }
}
