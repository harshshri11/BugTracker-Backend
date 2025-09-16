package com.wu.BugTracker.Service;

import com.wu.BugTracker.DTO.ProjectRequest;
import com.wu.BugTracker.Entity.Employee;
import com.wu.BugTracker.Entity.Project;
import com.wu.BugTracker.Exception.NotFoundException;
import com.wu.BugTracker.Repository.EmployeeRepository;
import com.wu.BugTracker.Repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository repo;
    private final EmployeeRepository employeeRepository;

    public ProjectService(ProjectRepository repo, EmployeeRepository employeeRepository) {
        this.repo = repo;
        this.employeeRepository = employeeRepository;
    }

    // Create Project

    
    public Project createProject(ProjectRequest request) {
        Employee manager = employeeRepository.findById(request.getManagerId())
                .orElseThrow(() -> new NotFoundException("Manager not found with id: " + request.getManagerId()));

        Project project = new Project();
        project.setProjectName(request.getProjectName());
        project.setDescription(request.getDescription());
        project.setManager(manager);

        return repo.save(project);
    }
    
    

    // Get Project by ID
    public Project getProjectById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Project not found with id: " + id));
    }

    // Get All Projects
    public List<Project> getAllProjects() {
        return repo.findAll();
    }

    // Update Project
    public Project updateProject(Long id, Project updated) {
        Project existing = getProjectById(id);

        existing.setProjectName(updated.getProjectName());
        existing.setDescription(updated.getDescription());

        if (updated.getManager() != null) {
            Long managerId = updated.getManager().getUserId();
            Employee manager = employeeRepository.findById(managerId)
                    .orElseThrow(() -> new NotFoundException("Manager not found with id: " + managerId));
            existing.setManager(manager);
        }

        return repo.save(existing);
    }

    // Delete Project
    public void deleteProject(Long id) {
        Project existing = getProjectById(id);
        repo.delete(existing);
    }
}
