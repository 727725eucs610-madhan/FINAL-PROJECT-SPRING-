package com.examly.springapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Profile;
import com.examly.springapp.repository.ProfileRepo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/profiles")
public class ProfileController {
    @Autowired
    private ProfileRepo profileRepo;
    
    @PostMapping
    public ResponseEntity<Profile> addProfile(@RequestBody Profile profile) {
      return new ResponseEntity<>(profileRepo.save(profile),HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles() {
        return ResponseEntity.ok(profileRepo.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long id) {
        return ResponseEntity.ok(profileRepo.findById(id).orElse(null));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Profile> updateProfile(
        @PathVariable Long id,
        @RequestBody Profile profile)
    {
        Profile existing = profileRepo.findById(id).orElse(null);
        if (existing != null) {
            existing.setFirstName(profile.getFirstName());
            existing.setLastName(profile.getLastName());
            existing.setAddress(profile.getAddress());
            existing.setDob(profile.getDob());
            existing.setGender(profile.getGender());
            return ResponseEntity.ok(profileRepo.save(existing));
        }
       return ResponseEntity.ok(null);
    }
    
    @GetMapping("/name/{firstName}")
    public ResponseEntity<List<Profile>> getProfileByName(@PathVariable String firstName) {
        return ResponseEntity.ok(profileRepo.findByFirstName(firstName));
    }
    
    @GetMapping("/search/{firstName}/{address}")
    public ResponseEntity<List<Profile>> getProfilesByNameAndAddress(
        @PathVariable String firstName, 
        @PathVariable String address) {
        return ResponseEntity.ok(profileRepo.findByFirstNameAndAddress(firstName, address));
    }
}
