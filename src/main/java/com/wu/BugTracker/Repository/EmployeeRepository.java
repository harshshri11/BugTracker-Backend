package com.wu.BugTracker.Repository;

import com.wu.BugTracker.Entity.Employee;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<Employee> findByEmail(String email);
    Optional<Employee> findByUsername(String username);

}
