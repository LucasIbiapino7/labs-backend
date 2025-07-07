package com.lab.backend.controllers;

import com.lab.backend.dtos.lab.LaboratorioCreateDto;
import com.lab.backend.dtos.lab.LaboratorioInfosDto;
import com.lab.backend.model.Laboratorio;
import com.lab.backend.services.LaboratorioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/laboratorio")
public class LaboratorioController {

    @Autowired
    private LaboratorioService laboratorioService;

    // PROTEGIDO
    @GetMapping("/{id}")
    public ResponseEntity<LaboratorioInfosDto> findById(@PathVariable Long id){
        LaboratorioInfosDto response = laboratorioService.findById(id);
        return ResponseEntity.ok(response);
    }

    // PROTEGIDO ADMIN MASTER
    @PostMapping()
    public ResponseEntity<LaboratorioInfosDto> insert(@Valid @RequestBody LaboratorioCreateDto dto){
        LaboratorioInfosDto response = laboratorioService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

}
