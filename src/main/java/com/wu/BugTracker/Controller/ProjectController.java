package com.wu.BugTracker.Controller;

import com.wu.BugTracker.DTO.ProjectRequest;
import com.wu.BugTracker.Entity.Project;
import com.wu.BugTracker.Service.ProjectService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // Create Project
//    @PostMapping
//    public ResponseEntity<Project> createProject(@RequestBody Project project) {
//        Project saved = projectService.createProject(project);
//        return ResponseEntity.ok(saved);
//    }
    
    @PostMapping
    @PreAuthorize("hasRole('manager')")
    public ResponseEntity<Project> createProject(@Valid @RequestBody ProjectRequest request) {
        Project saved = projectService.createProject(request);
        return ResponseEntity.ok(saved);
    }

    // Get Project by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('manager')")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Project project = projectService.getProjectById(id);
        return ResponseEntity.ok(project);
    }

    // Get All Projects
    @GetMapping
    @PreAuthorize("hasRole('manager')")
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    // Update Project
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('manager')")
    public ResponseEntity<Project> updateProject(@PathVariable Long id,
                                                 @RequestBody Project updated) {
        Project saved = projectService.updateProject(id, updated);
        return ResponseEntity.ok(saved);
    }

    // Delete Project
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('manager')")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
