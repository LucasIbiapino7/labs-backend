package com.lab.backend.controllers;

import com.lab.backend.dtos.lab.*;
import com.lab.backend.dtos.profile.ProfileMemberDto;
import com.lab.backend.dtos.profile.ProfileMinDto;
import com.lab.backend.model.Laboratorio;
import com.lab.backend.services.LaboratorioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/laboratorio")
public class LaboratorioController {

    @Autowired
    private LaboratorioService laboratorioService;

    // PROTEGIDO
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<LaboratorioInfosDto> findById(@PathVariable Long id){
        LaboratorioInfosDto response = laboratorioService.findById(id);
        return ResponseEntity.ok(response);
    }

    // PROTEGIDO ADMIN MASTER
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping()
    public ResponseEntity<LaboratorioInfosDto> insert(@Valid @RequestBody LaboratorioCreateDto dto){
        LaboratorioInfosDto response = laboratorioService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<LaboratorioInfosDto> update(@PathVariable Long id, @RequestBody LaboratorioUpdateDto dto){
        LaboratorioInfosDto response = laboratorioService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    // PROTEGIDO ADMIN MASTER
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        laboratorioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PatchMapping("/{labId}/members/{profileId}")
    public ResponseEntity<Void> updateLabRoleOwner(@PathVariable Long labId, @PathVariable Long profileId){
        laboratorioService.updateLabRoleOwner(labId, profileId);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/{labId}/members")
    public ResponseEntity<Void> addMember(@RequestBody @Valid LabAddMemberDto dto, @PathVariable Long labId){
        laboratorioService.addMember(dto, labId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{labId}/members")
    public ResponseEntity<Page<ProfileMemberDto>> getAllMembers(@PathVariable Long labId, @RequestParam(name = "nome", defaultValue = "") String nome, Pageable pageable){
        Page<ProfileMemberDto> response = laboratorioService.getAllMembers(labId, nome, pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/summary")
    public ResponseEntity<LabSummaryDto> summary(@PathVariable Long id){
        LabSummaryDto response = laboratorioService.summary(id);
        return ResponseEntity.ok(response);
    }


}
