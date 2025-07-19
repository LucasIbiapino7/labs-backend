package com.lab.backend.controllers;

import com.lab.backend.dtos.materiais.InsertMaterialDto;
import com.lab.backend.dtos.materiais.MaterialDto;
import com.lab.backend.dtos.materiais.UpdateMaterialDto;
import com.lab.backend.model.enums.MaterialType;
import com.lab.backend.services.MaterialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping("/{labId}")
    public ResponseEntity<Page<MaterialDto>> getMateriaisPublicos(@PathVariable Long labId,
                                                                  @RequestParam(name = "nome", defaultValue = "") String nome,
                                                                  @RequestParam(name = "tipo", required = false) MaterialType tipo,
                                                                  Pageable pageable){
        Page<MaterialDto> response = materialService.getMateriaisPublicos(labId, nome, tipo, pageable);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/{labId}/private")
    public ResponseEntity<Page<MaterialDto>> getMateriaisPublicosEPrivados(@PathVariable Long labId,
                                                                           @RequestParam(name = "nome", defaultValue = "") String nome,
                                                                           @RequestParam(name = "tipo", required = false) MaterialType tipo,
                                                                           Pageable pageable){
        Page<MaterialDto> response = materialService.getMateriaisPublicosEPrivados(labId, nome, tipo, pageable);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/{labId}")
    public ResponseEntity<MaterialDto> insert(@PathVariable Long labId, @RequestBody @Valid InsertMaterialDto dto){
        MaterialDto response = materialService.insert(labId, dto);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping("/{labId}/{materialId}")
    public ResponseEntity<MaterialDto> update(@PathVariable Long labId, @PathVariable Long materialId, @RequestBody UpdateMaterialDto dto){
        MaterialDto response = materialService.update(labId, materialId, dto);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @DeleteMapping("/{labId}/{materialId}")
    public ResponseEntity<Void> delete(@PathVariable Long materialId, @PathVariable Long labId){
        materialService.delete(materialId, labId);
        return ResponseEntity.noContent().build();
    }

}
