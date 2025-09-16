package com.wu.BugTracker.Mapper;

import com.wu.BugTracker.DTO.ProjectRequest;
import com.wu.BugTracker.Entity.Employee;
import com.wu.BugTracker.Entity.Project;

public class ProjectMapper {

    // DTO -> Entity
    public static Project toEntity(ProjectRequest request, Employee manager) {
        Project project = new Project();
        project.setProjectName(request.getProjectName());
        project.setDescription(request.getDescription());
        project.setManager(manager);
        return project;
    }

    // Entity -> DTO (optional, useful if you want to send back ProjectRequest-like data)
    public static ProjectRequest toDto(Project project) {
        ProjectRequest dto = new ProjectRequest();
        dto.setProjectName(project.getProjectName());
        dto.setDescription(project.getDescription());
        if (project.getManager() != null) {
            dto.setManagerId(project.getManager().getUserId());
        }
        return dto;
    }
}

