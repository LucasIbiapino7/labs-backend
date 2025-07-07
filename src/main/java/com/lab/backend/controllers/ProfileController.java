package com.lab.backend.controllers;

import com.lab.backend.dtos.profile.ProfileMinDto;
import com.lab.backend.dtos.profile.ProfileUpdateDto;
import com.lab.backend.services.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    // PUBLICO
    @GetMapping("/{id}")
    public ResponseEntity<ProfileMinDto> findById(@PathVariable Long id){
        ProfileMinDto response = profileService.findById(id);
        return ResponseEntity.ok(response);
    }

    // PROTEGIDO
    @GetMapping()
    public ResponseEntity<ProfileMinDto> getMe(){
        ProfileMinDto response = profileService.getMe();
        return ResponseEntity.ok(response);
    }

    // PROTEGIDO
    @PutMapping()
    public ResponseEntity<ProfileMinDto> update(@Valid @RequestBody ProfileUpdateDto dto){
        ProfileMinDto response = profileService.update(dto);
        return ResponseEntity.ok(response);
    }
}
