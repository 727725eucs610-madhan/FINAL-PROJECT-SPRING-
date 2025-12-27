package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.AccountStatusLog;
import com.examly.springapp.repository.AccountStatusLogRepo;

import java.util.List;

@RestController
@RequestMapping("/api/accountStatusLogs")
public class AccountStatusLogController {
    
    @Autowired
    private AccountStatusLogRepo accountStatusLogRepo;
    
    @PostMapping
    public ResponseEntity<AccountStatusLog> addAccountStatusLog(@RequestBody AccountStatusLog log) {
        return new ResponseEntity<>(accountStatusLogRepo.save(log), HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<AccountStatusLog>> getAllAccountStatusLogs() {
        return ResponseEntity.ok(accountStatusLogRepo.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AccountStatusLog> getAccountStatusLogById(@PathVariable Long id) {
        return ResponseEntity.ok(accountStatusLogRepo.findById(id).orElse(null));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AccountStatusLog> updateAccountStatusLog(
        @PathVariable Long id,
        @RequestBody AccountStatusLog log) {
        AccountStatusLog existing = accountStatusLogRepo.findById(id).orElse(null);
        if (existing != null) {
            existing.setUser(log.getUser());
            existing.setStatus(log.getStatus());
            existing.setTimestamp(log.getTimestamp());
            return ResponseEntity.ok(accountStatusLogRepo.save(existing));
        }
        return ResponseEntity.ok(null);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccountStatusLog(@PathVariable Long id) {
        accountStatusLogRepo.deleteById(id);
        return ResponseEntity.ok("Account status log deleted successfully");
    }
}
