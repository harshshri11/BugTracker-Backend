package com.wu.BugTracker.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.wu.BugTracker.DTO.EmployeeRequest;
import com.wu.BugTracker.Entity.Employee;
import com.wu.BugTracker.Repository.UserRepo;
import com.wu.BugTracker.Service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final UserRepo userRepo;
    private final EmployeeService service;

    public EmployeeController(EmployeeService service, UserRepo userRepo) {
        this.service = service;
        this.userRepo = userRepo;
    }

    @GetMapping
    @PreAuthorize("hasRole('admin')")
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('admin','manager')")
    public Employee getEmployeeById(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeRequest request) {
        Employee saved = service.createEmployee(request);
        Employee response = new Employee();
        response.setUserId(saved.getUserId());
        response.setUsername(saved.getUsername());
        response.setEmail(saved.getEmail());
        response.setRole(saved.getRole());
        response.setTeam(saved.getTeam());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('admin','manager')")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updated) {
        return service.updateEmployee(id, updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/info")
//    @PreAuthorize("isAuthenticated()")
//    public Employee getUserDetails() {
//        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        // principal is set to email in our filter and userDetails
//        return userRepo.findByEmail(principal).orElseThrow(() -> new RuntimeException("User not found"));
//    }

//    @GetMapping("/admin")
//    @PreAuthorize("hasRole('admin')")
//    public String adminOnly() {
//        return "Only admins can see this!";
//    }
}
