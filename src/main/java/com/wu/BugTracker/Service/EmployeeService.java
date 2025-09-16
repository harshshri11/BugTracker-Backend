package com.wu.BugTracker.Service;

import com.wu.BugTracker.DTO.EmployeeRequest;
import com.wu.BugTracker.Entity.Employee;
import com.wu.BugTracker.Exception.NotFoundException;
import com.wu.BugTracker.Repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
    }

    
    public Employee createEmployee(EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setUsername(request.getUsername());
        employee.setEmail(request.getEmail());
        employee.setPassword(request.getPassword()); // later we’ll hash this
        employee.setRole(request.getRole());
        employee.setTeam(request.getTeam());

        return repo.save(employee);
    }


    public Employee updateEmployee(Long id, Employee updated) {
        Employee emp = getEmployeeById(id);
        emp.setUsername(updated.getUsername());
        emp.setEmail(updated.getEmail());
        emp.setPassword(updated.getPassword());
        emp.setRole(updated.getRole());
        emp.setTeam(updated.getTeam());
        return repo.save(emp);
    }

    public void deleteEmployee(Long id) {
        Employee emp = getEmployeeById(id);
        repo.delete(emp);
    }
}
