package com.wu.BugTracker.Repository;

import com.wu.BugTracker.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
