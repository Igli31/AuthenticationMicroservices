package com.example.class_service.Service;

import com.example.class_service.Entity.Role;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {

    // Temporary hardcoded credentials - replace with database implementation
    public boolean authenticate(String username, String password) {
        return "admin".equals(username) && "securePassword123!".equals(password) ||
                ("customer".equals(username) && "customerPass456!".equals(password)) ||
                ("manager".equals(username) && "managerPass789!".equals(password));
    }

    public Set<Role> getUserRoles(String username) {
        // Replace with database lookup in production
        return switch (username) {
            case "admin" -> Set.of(Role.ADMIN);
            case "manager" -> Set.of(Role.MANAGER);
            case "customer" -> Set.of(Role.CUSTOMER);
            default -> Set.of();
        };
    }
}