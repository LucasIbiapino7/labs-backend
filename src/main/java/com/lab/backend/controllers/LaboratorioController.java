package com.lab.backend.controllers;

import com.lab.backend.dtos.lab.LaboratorioCreateDto;
import com.lab.backend.dtos.lab.LaboratorioInfosDto;
import com.lab.backend.dtos.lab.LaboratorioUpdateDto;
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

    // PROTEGIDO
    @PutMapping("/{id}")
    public ResponseEntity<LaboratorioInfosDto> update(@PathVariable Long id, @RequestBody LaboratorioUpdateDto dto){
        LaboratorioInfosDto response = laboratorioService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    // PROTEGIDO ADMIN MASTER
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        laboratorioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
