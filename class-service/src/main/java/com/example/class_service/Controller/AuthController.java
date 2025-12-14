package com.example.class_service.Controller;


import com.example.class_service.Configuration.JwtUtil;
import com.example.class_service.DTO.LoginRequest;
import com.example.class_service.Entity.Role;
import com.example.class_service.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (authService.authenticate(request.getUsername(), request.getPassword())) {
            Set<Role> roles = authService.getUserRoles(request.getUsername());
            String token = jwtUtil.generateToken(request.getUsername(), roles);

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("roles", roles);
            return ResponseEntity.ok(response);
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
