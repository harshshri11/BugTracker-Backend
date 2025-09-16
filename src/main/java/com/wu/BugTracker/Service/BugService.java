package com.wu.BugTracker.Service;

import com.wu.BugTracker.DTO.BugRequest;
import com.wu.BugTracker.Entity.Bug;
import com.wu.BugTracker.Entity.Employee;
import com.wu.BugTracker.Entity.Project;
import com.wu.BugTracker.Exception.NotFoundException;
import com.wu.BugTracker.Repository.BugRepository;
import com.wu.BugTracker.Repository.EmployeeRepository;
import com.wu.BugTracker.Repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class BugService {

    private final BugRepository bugRepository;
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    public BugService(BugRepository bugRepository, ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        this.bugRepository = bugRepository;
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }

    // Create Bug
    public Bug createBug(BugRequest request) {
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new NotFoundException("Project not found with id: " + request.getProjectId()));

        Employee reporter = employeeRepository.findById(request.getReporterId())
                .orElseThrow(() -> new NotFoundException("Reporter not found with id: " + request.getReporterId()));

        Employee assignee = null;
        if (request.getAssigneeId() != null) {
            assignee = employeeRepository.findById(request.getAssigneeId())
                    .orElseThrow(() -> new NotFoundException("Assignee not found with id: " + request.getAssigneeId()));
        }

        Bug bug = new Bug();
        bug.setTitle(request.getTitle());
        bug.setDescription(request.getDescription());
        bug.setPriority(request.getPriority());
        bug.setStatus("backlog"); // Always start in backlog
        bug.setProject(project);
        bug.setReporter(reporter);
        bug.setAssignee(assignee);

        return bugRepository.save(bug);
    }

    public Bug getBugById(Long id) {
        return bugRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Bug not found with id: " + id));
    }

    public List<Bug> getAllBugs() {
        return bugRepository.findAll();
    }

    // Admin/Manager can update full bug
    public Bug updateBug(Long id, Bug updated) {
        Bug existing = getBugById(id);

        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setPriority(updated.getPriority());
        existing.setStatus(updated.getStatus());

        if (updated.getProject() != null) {
            Long projectId = updated.getProject().getProjectId();
            Project project = projectRepository.findById(projectId)
                    .orElseThrow(() -> new NotFoundException("Project not found with id: " + projectId));
            existing.setProject(project);
        }

        if (updated.getReporter() != null) {
            Long reporterId = updated.getReporter().getUserId();
            Employee reporter = employeeRepository.findById(reporterId)
                    .orElseThrow(() -> new NotFoundException("Reporter not found with id: " + reporterId));
            existing.setReporter(reporter);
        }

        if (updated.getAssignee() != null && updated.getAssignee().getUserId() != null) {
            Long assigneeId = updated.getAssignee().getUserId();
            Employee assignee = employeeRepository.findById(assigneeId)
                    .orElseThrow(() -> new NotFoundException("Assignee not found with id: " + assigneeId));
            existing.setAssignee(assignee);
        }

        return bugRepository.save(existing);
    }

    // ---- Restricted transitions ----

    public Bug moveToInProgress(Long id) {
        Bug bug = getBugById(id);
        if (!"backlog".equalsIgnoreCase(bug.getStatus())) {
            throw new IllegalStateException("Bug must be in BACKLOG to move to INPROGRESS");
        }
        bug.setStatus("inprogress");
        return bugRepository.save(bug);
    }

    public Bug moveToValidate(Long id) {
        Bug bug = getBugById(id);
        if (!"inprogress".equalsIgnoreCase(bug.getStatus())) {
            throw new IllegalStateException("Bug must be INPROGRESS to move to VALIDATE");
        }
        bug.setStatus("validate");
        return bugRepository.save(bug);
    }

    public Bug moveToResolved(Long id) {
        Bug bug = getBugById(id);
        if (!"validate".equalsIgnoreCase(bug.getStatus())) {
            throw new IllegalStateException("Bug must be VALIDATE to move to RESOLVED");
        }
        bug.setStatus("resolved");
        bug.setResolvedAt(Instant.now());
        return bugRepository.save(bug);
    }

    public Bug moveBackToBacklog(Long id) {
        Bug bug = getBugById(id);
        if (!"validate".equalsIgnoreCase(bug.getStatus())) {
            throw new IllegalStateException("Bug must be VALIDATE to move back to BACKLOG");
        }
        bug.setStatus("backlog");
        bug.setAssignee(null);
        return bugRepository.save(bug);
    }

    public void deleteBug(Long id) {
        Bug existing = getBugById(id);
        bugRepository.delete(existing);
    }
    public Bug assignBugToDeveloper(Long bugId, Long developerId) {
        Bug bug = getBugById(bugId);
        if (!"backlog".equalsIgnoreCase(bug.getStatus())) {
            throw new IllegalStateException("Bug must be backlog to move back to assign");
        }
        Employee developer = employeeRepository.findById(developerId)
                .orElseThrow(() -> new NotFoundException("Developer not found with id: " + developerId));

        if (!"DEVELOPER".equalsIgnoreCase(developer.getRole())) {
            throw new IllegalArgumentException("User is not a developer");
        }

        bug.setAssignee(developer);
        bug.setStatus("inprogress"); // when manager assigns, bug leaves backlog

        return bugRepository.save(bug);
    }
}
