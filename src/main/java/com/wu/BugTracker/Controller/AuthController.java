package com.wu.BugTracker.Controller;

import java.util.Collections;
import java.util.Map;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wu.BugTracker.DTO.EmployeeRequest;
import com.wu.BugTracker.Entity.Employee;
import com.wu.BugTracker.model.LoginCreds;
import com.wu.BugTracker.Repository.EmployeeRepository;
import com.wu.BugTracker.Repository.UserRepo;
import com.wu.BugTracker.security.JwtUtil;
import com.wu.BugTracker.security.MyUserDetailService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
//    private UserRepo userRepo;
    private EmployeeRepository userRepo;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MyUserDetailService userDetailService;

    // ✅ Anyone can register
    @PostMapping("/register")
    public Map<String, Object> registerHandler(@RequestBody Employee employee) {
        // ❌ ADMIN-only enforcement (commented)
        
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth == null || !auth.getAuthorities().stream()
//                .anyMatch(grantedAuth -> grantedAuth.getAuthority().equals("ADMIN"))) {
//            return Collections.singletonMap("error", "Only ADMIN can register new users.");
//        }
//        
    	System.out.println("entered the registration processs");
        // Encode password
        String encodedPass = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(encodedPass);
        System.out.println(employee.getPassword() + " password" + employee.getUsername() + " username" + employee.getEmail() + employee.getRole());
        // Assign default role if not provided
        if (employee.getRole() == null || employee.getRole().isBlank()) {
            employee.setRole("USER");
        }

        // Save user
        
        employee = userRepo.save(employee);
        	System.out.println(employee);
        	// Build UserDetails
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(employee.getUsername())
                .password(employee.getPassword())
                .roles(employee.getRole())
                .build();

        // Generate JWT
        String token = jwtUtil.generateToken(userDetails);

        return Collections.singletonMap("jwt-token", token);
    }

    // ✅ Login existing user
    @PostMapping("/login")
    public Map<String, Object> loginHandler(@RequestBody EmployeeRequest body) {
        try {
            // Authenticate user credentials
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword());
            authenticationManager.authenticate(authInputToken);

            // Load user details
            UserDetails userDetails = userDetailService.loadUserByUsername(body.getUsername());

            // Generate JWT token
            String token = jwtUtil.generateToken(userDetails);

            // Get role from UserDetails authorities
            String role = userDetails.getAuthorities().stream()
                    .findFirst()
                    .map(auth -> auth.getAuthority().replace("ROLE_", ""))
                    .orElse("USER");

            // Return JWT and role
            return Map.of(
                    "jwt-token", token,
                    "role", role
            );

        } catch (AuthenticationException authExc) {
            return Collections.singletonMap("error", "Invalid username/password.");
        }
    }


}
