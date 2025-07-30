package com.campypro.campyprobackend.controller;

import com.campypro.campyprobackend.entity.User;
import com.campypro.campyprobackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.getUserByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
    Optional<User> existingUserOpt = userService.getUserById(id);
    if (existingUserOpt.isEmpty()) {
        return ResponseEntity.notFound().build();
    }
    User existingUser = existingUserOpt.get();

    // Sadece null olmayan alanları güncelle
    if (updatedUser.getEmail() != null) {
        existingUser.setEmail(updatedUser.getEmail());
    }
    if (updatedUser.getPhone() != null) {
        existingUser.setPhone(updatedUser.getPhone());
    }
    if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
        existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
    }

    return ResponseEntity.ok(userService.saveUser(existingUser));
}
} 