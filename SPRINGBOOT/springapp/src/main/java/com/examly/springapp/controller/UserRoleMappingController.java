package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.UserRoleMapping;
import com.examly.springapp.repository.UserRoleMappingRepo;

import java.util.List;

@RestController
@RequestMapping("/api/userRoleMappings")
public class UserRoleMappingController {
    
    @Autowired
    private UserRoleMappingRepo userRoleMappingRepo;
    
    @PostMapping
    public ResponseEntity<UserRoleMapping> addUserRoleMapping(@RequestBody UserRoleMapping userRoleMapping) {
        return new ResponseEntity<>(userRoleMappingRepo.save(userRoleMapping), HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<UserRoleMapping>> getAllUserRoleMappings() {
        return ResponseEntity.ok(userRoleMappingRepo.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserRoleMapping> getUserRoleMappingById(@PathVariable Long id) {
        return ResponseEntity.ok(userRoleMappingRepo.findById(id).orElse(null));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserRoleMapping> updateUserRoleMapping(
        @PathVariable Long id,
        @RequestBody UserRoleMapping userRoleMapping) {
        UserRoleMapping existing = userRoleMappingRepo.findById(id).orElse(null);
        if (existing != null) {
            existing.setUser(userRoleMapping.getUser());
            existing.setRole(userRoleMapping.getRole());
            return ResponseEntity.ok(userRoleMappingRepo.save(existing));
        }
        return ResponseEntity.ok(null);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserRoleMapping>> getUserRoleMappingsByUserId(@PathVariable Long userId) {
        List<UserRoleMapping> mappings = userRoleMappingRepo.findByUserId(userId);
        if (mappings.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mappings);
    }
    
    @GetMapping("/user/{userId}/role/{roleId}")
    public ResponseEntity<UserRoleMapping> getUserRoleMappingByUserIdAndRoleId(
        @PathVariable Long userId, 
        @PathVariable Long roleId) {
        UserRoleMapping mapping = userRoleMappingRepo.findByUserIdAndRoleId(userId, roleId);
        return ResponseEntity.ok(mapping);
    }
}