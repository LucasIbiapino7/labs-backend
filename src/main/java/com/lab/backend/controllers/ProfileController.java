package com.lab.backend.controllers;

import com.lab.backend.dtos.profile.ProfileMinDto;
import com.lab.backend.dtos.profile.ProfileUpdateDto;
import com.lab.backend.services.ProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Perfis")
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
    @GetMapping("/me")
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

    // PROTEGIDO APENAS PARA O MAIOR ADMIN
    @GetMapping()
    public ResponseEntity<Page<ProfileMinDto>> findByName(@RequestParam(name = "nome", defaultValue = "") String nome, Pageable pageable){
        Page<ProfileMinDto> response = profileService.findByName(nome, pageable);
        return ResponseEntity.ok(response);
    }
}
