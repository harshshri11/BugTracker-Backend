package com.wu.BugTracker.Mapper;

import com.wu.BugTracker.DTO.BugResponse;
import com.wu.BugTracker.Entity.Bug;

public class BugMapper {

    public static BugResponse toResponse(Bug bug) {
        BugResponse response = new BugResponse();
        response.setBugId(bug.getBugId());
        response.setTitle(bug.getTitle());
        response.setDescription(bug.getDescription());
        response.setPriority(bug.getPriority());
        response.setStatus(bug.getStatus());
        response.setCreatedAt(bug.getCreatedAt());
        response.setResolvedAt(bug.getResolvedAt());

        if (bug.getProject() != null) {
            response.setProjectId(bug.getProject().getProjectId());
            response.setProjectName(bug.getProject().getProjectName());
        }

        if (bug.getReporter() != null) {
            response.setReporterId(bug.getReporter().getUserId());
            response.setReporterName(bug.getReporter().getUsername());
        }

        if (bug.getAssignee() != null) {
            response.setAssigneeId(bug.getAssignee().getUserId());
            response.setAssigneeName(bug.getAssignee().getUsername());
        }

        return response;
    }
}
