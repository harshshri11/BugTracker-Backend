package com.wu.BugTracker.Controller;

import com.wu.BugTracker.DTO.BugRequest;
import com.wu.BugTracker.Entity.Bug;
import com.wu.BugTracker.Service.BugService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bugs")
public class BugController {

    private final BugService bugService;

    public BugController(BugService bugService) {
        this.bugService = bugService;
    }

    // Create Bug (only testers can create)
    @PostMapping
    @PreAuthorize("hasRole('tester')")
    public ResponseEntity<Bug> createBug(@Valid @RequestBody BugRequest request) {
        Bug saved = bugService.createBug(request);
        return ResponseEntity.ok(saved);
    }

    // Get Bug by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('tester','manager','developer','admin')")
    public ResponseEntity<Bug> getBugById(@PathVariable Long id) {
        Bug bug = bugService.getBugById(id);
        return ResponseEntity.ok(bug);
    }

    // Get All Bugs
    @GetMapping
    @PreAuthorize("hasAnyRole('tester','manager','developer','admin')")
    public ResponseEntity<List<Bug>> getAllBugs() {
        List<Bug> bugs = bugService.getAllBugs();
        return ResponseEntity.ok(bugs);
    }

    // Generic Update Bug (only admins & managers can update everything)
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('admin','manager')")
    public ResponseEntity<Bug> updateBug(@PathVariable Long id, @RequestBody Bug updated) {
        Bug saved = bugService.updateBug(id, updated);
        return ResponseEntity.ok(saved);
    }

    // Transition: Developer starts work (backlog → inprogress)
    @PutMapping("/{id}/start")
    @PreAuthorize("hasRole('developer')")
    public ResponseEntity<Bug> startBug(@PathVariable Long id) {
        Bug bug = bugService.moveToInProgress(id);
        return ResponseEntity.ok(bug);
    }

    // Transition: Developer resolves (inprogress → validate)
    @PutMapping("/{id}/resolve")
    @PreAuthorize("hasRole('developer')")
    public ResponseEntity<Bug> resolveBug(@PathVariable Long id) {
        Bug bug = bugService.moveToValidate(id);
        return ResponseEntity.ok(bug);
    }

    // Transition: Tester verifies (validate → resolved OR back to backlog)
    @PutMapping("/{id}/verify")
    @PreAuthorize("hasRole('tester')")
    public ResponseEntity<Bug> verifyBug(@PathVariable Long id, @RequestParam boolean passed) {
        Bug bug = passed ? bugService.moveToResolved(id) : bugService.moveBackToBacklog(id);
        return ResponseEntity.ok(bug);
    }

    // Delete Bug (tester or manager can delete)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('tester','manager')")
    public ResponseEntity<Void> deleteBug(@PathVariable Long id) {
        bugService.deleteBug(id);
        return ResponseEntity.noContent().build();
    }
    
    // Assign bug to developer (Manager only)
    @PutMapping("/{bugId}/assign")
    @PreAuthorize("hasRole('manager')")
    public ResponseEntity<Bug> assignBugToDeveloper(
            @PathVariable Long bugId,
            @RequestBody Map<String, Long> requestBody) {

        Long assigneeId = requestBody.get("assigneeId");
        Bug updated = bugService.assignBugToDeveloper(bugId, assigneeId);
        return ResponseEntity.ok(updated);
    }
}
