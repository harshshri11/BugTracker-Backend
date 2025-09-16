package com.wu.BugTracker.Repository;

import java.util.Optional;

import com.wu.BugTracker.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUsername(String username);
}

