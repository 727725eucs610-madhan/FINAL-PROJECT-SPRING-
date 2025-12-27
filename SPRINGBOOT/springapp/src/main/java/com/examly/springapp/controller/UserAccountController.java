package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.UserAccount;
import com.examly.springapp.repository.UserAccountRepo;

@RestController
@RequestMapping("/api/users")
public class UserAccountController {
    @Autowired
    private UserAccountRepo repo;
    @PostMapping
    public ResponseEntity<UserAccount> createUser(@RequestBody UserAccount user) {
        return ResponseEntity.ok(repo.save(user));
    }
}
