package com.campypro.campyprobackend.controller;

import com.campypro.campyprobackend.entity.Address;
import com.campypro.campyprobackend.entity.User;
import com.campypro.campyprobackend.security.JwtUtil;
import com.campypro.campyprobackend.service.AddressService;
import com.campypro.campyprobackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    private final AddressService addressService;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    @Autowired
    public AddressController(AddressService addressService, UserService userService, JwtUtil jwtUtil) {
        this.addressService = addressService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public List<Address> getMyAddresses(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String email = jwtUtil.extractUsername(token);
        Optional<User> userOpt = userService.getUserByEmail(email);
        if (userOpt.isEmpty()) return List.of();
        return addressService.getAddressesByUser(userOpt.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        Optional<Address> address = addressService.getAddressById(id);
        return address.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address, @RequestHeader("Authorization") String authHeader) {
        // JWT'den email'i çıkar
        String token = authHeader.replace("Bearer ", "");
        String email = jwtUtil.extractUsername(token);
        Optional<User> userOpt = userService.getUserByEmail(email);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(401).build();
        }
        address.setUser(userOpt.get());
        Address saved = addressService.saveAddress(address);
        return ResponseEntity.ok(saved);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
} 