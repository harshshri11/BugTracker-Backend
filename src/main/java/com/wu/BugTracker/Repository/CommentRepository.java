package com.wu.BugTracker.Repository;

import com.wu.BugTracker.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBug_BugId(Long bugId);
    List<Comment> findByAuthor_UserId(Long userId);
}
