package com.campypro.campyprobackend.controller;

import com.campypro.campyprobackend.entity.User;
import com.campypro.campyprobackend.service.UserService;
import com.campypro.campyprobackend.security.JwtUtil;
import com.campypro.campyprobackend.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(UserService userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        // Email zaten kayıtlı mı kontrol et
        if (userService.getUserByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        User saved = userService.saveUser(user);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("Login attempt for email: " + loginRequest.getEmail());
        
        Optional<User> userOpt = userService.getUserByEmail(loginRequest.getEmail());
        if (userOpt.isEmpty()) {
            System.out.println("User not found for email: " + loginRequest.getEmail());
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        
        User user = userOpt.get();
        System.out.println("User found: " + user.getEmail() + ", Role: " + user.getRole());
        System.out.println("Stored password hash: " + user.getPassword());
        
        boolean passwordMatches = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
        System.out.println("Password matches: " + passwordMatches);
        
        if (!passwordMatches) {
            System.out.println("Password does not match for user: " + loginRequest.getEmail());
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        
        // JWT token üret
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole(), user.getFirstName(), user.getLastName());
        System.out.println("Login successful for user: " + user.getEmail());
        return ResponseEntity.ok(Map.of("token", token));
    }
} 