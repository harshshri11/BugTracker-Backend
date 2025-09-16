package com.wu.BugTracker.Mapper;

import com.wu.BugTracker.DTO.EmployeeResponse;
import com.wu.BugTracker.Entity.Employee;

public class EmployeeMapper {

    public static EmployeeResponse toResponse(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getUserId());
        response.setUsername(employee.getUsername());
        response.setEmail(employee.getEmail());
        response.setRole(employee.getRole());
        response.setTeam(employee.getTeam());
        return response;
    }
}
