package com.wu.BugTracker.security;

import com.wu.BugTracker.Entity.Employee;
import com.wu.BugTracker.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class MyUserDetailService implements UserDetailsService {

    @Autowired 
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> userRes = userRepo.findByUsername(username);

        if (userRes.isEmpty()) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }

        Employee user = userRes.get();

        // Make sure role is not null
        String role = user.getRole();
        if (role == null || role.isBlank()) {
            throw new UsernameNotFoundException("User " + username + " has no role assigned.");
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role)))
                .build();
    }
}
