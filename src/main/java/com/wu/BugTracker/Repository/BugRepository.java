package com.wu.BugTracker.Repository;

import com.wu.BugTracker.Entity.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BugRepository extends JpaRepository<Bug, Long> {
    List<Bug> findByStatus(String status);
    List<Bug> findByPriority(String priority);
    List<Bug> findByProject_ProjectId(Long projectId);
    List<Bug> findByAssignee_UserId(Long userId);
}
