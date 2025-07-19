package com.lab.backend.controllers;

import com.lab.backend.dtos.admin.AdminLabListDto;
import com.lab.backend.dtos.admin.ProfileTypeUpdateDto;
import com.lab.backend.dtos.profile.ProfileMinDto;
import com.lab.backend.services.LaboratorioService;
import com.lab.backend.services.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private LaboratorioService laboratorioService;

    @Autowired
    private ProfileService profileService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/labs")
    public ResponseEntity<Page<AdminLabListDto>> list(Pageable pageable){
        Page<AdminLabListDto> response = laboratorioService.listLabsAdminView(pageable);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/profiles/{profileId}/type")
    public ResponseEntity<ProfileMinDto> updateProfileType(@PathVariable Long profileId, @Valid @RequestBody ProfileTypeUpdateDto dto) {
        ProfileMinDto updated = profileService.updateProfileType(profileId, dto.getProfileType());
        return ResponseEntity.ok(updated);
    }
}
