package com.example.class_service.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/customers")
public class AdminController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/dashboard")
    public ResponseEntity<String> adminDashboard() {
        return ResponseEntity.ok("Welcome to Customer Service Admin Dashboard");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/statistics")
    public ResponseEntity<String> getCustomerStatistics() {
        return ResponseEntity.ok("Customer statistics data");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{customerId}/lock")
    public ResponseEntity<String> lockCustomerAccount(@PathVariable Long customerId) {
        // Implementation to lock customer account
        return ResponseEntity.ok("Customer account locked");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{customerId}/unlock")
    public ResponseEntity<String> unlockCustomerAccount(@PathVariable Long customerId) {
        // Implementation to unlock customer account
        return ResponseEntity.ok("Customer account unlocked");
    }
}
