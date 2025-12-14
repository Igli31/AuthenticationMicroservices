package com.example.class_service.Configuration;

import com.example.class_service.Entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtUtil {

    // Use a proper Base64 encoded secret key (different from Order Service)
    private final String secretKey = "VGhpcyBpcyBhIGRpZmZlcmVudCBzZWNyZXQga2V5IQ==";

    // Create a secure key instance
    private final SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());;

    public String generateToken(String username, Set<Role> roles) {
        return Jwts.builder()
                .subject(username)
                .claim("roles", roles.stream().map(Role::name).toList())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiration
                .signWith(key, Jwts.SIG.HS256) // Use the secure key
                .compact();
    }

    // Update getClaims to extract roles
    public Set<Role> extractRoles(String token) {
        List<String> roles = getClaims(token).get("roles", List.class);
        return roles.stream()
                .map(Role::valueOf)
                .collect(Collectors.toSet());
    }

    // Validate JWT token
    public boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    // Extract username from token
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // Extract expiration date from token
    private Date extractExpiration(String token) {
        return getClaims(token).getExpiration();
    }

    // Check if token is expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Helper method to get Claims from the JWT token
    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(key) // Use the same key for verification
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isValidToken(String token, String username) {
        try {
            String tokenUsername = extractUsername(token);
            Date expiration = extractExpiration(token);
            return tokenUsername.equals(username) && expiration.after(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
