package com.lab.backend.controllers;

import com.lab.backend.dtos.materiais.MaterialDto;
import com.lab.backend.model.enums.MaterialType;
import com.lab.backend.services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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

    // Protegido
    @GetMapping("/{labId}/private")
    public ResponseEntity<Page<MaterialDto>> getMateriaisPublicosEPrivados(@PathVariable Long labId,
                                                                           @RequestParam(name = "nome", defaultValue = "") String nome,
                                                                           @RequestParam(name = "tipo", required = false) MaterialType tipo,
                                                                           Pageable pageable){
        Page<MaterialDto> response = materialService.getMateriaisPublicosEPrivados(labId, nome, tipo, pageable);
        return ResponseEntity.ok(response);
    }

    // insert
    // update
    // delete
}
