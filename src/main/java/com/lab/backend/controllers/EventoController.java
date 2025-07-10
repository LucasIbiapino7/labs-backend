package com.lab.backend.controllers;

import com.lab.backend.dtos.eventos.EventoDto;
import com.lab.backend.dtos.eventos.EventoInsertDto;
import com.lab.backend.dtos.eventos.EventoMinDto;
import com.lab.backend.dtos.eventos.UpdateEventoDto;
import com.lab.backend.services.EventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evento")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping("/{labId}")
    public ResponseEntity<Page<EventoDto>> feedEventosLab(@PathVariable Long labId, Pageable pageable){
        Page<EventoDto> response = eventoService.feedEventosLab(labId, pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{labId}/next")
    public ResponseEntity<List<EventoMinDto>> getNextEvents(@PathVariable Long labId){
        List<EventoMinDto> response = eventoService.getNextEvents(labId);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/{labId}")
    public ResponseEntity<EventoDto> insert(@PathVariable Long labId,@Valid @RequestBody EventoInsertDto dto){
        EventoDto response = eventoService.insert(labId, dto);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping("/{labId}/{eventoId}")
    public ResponseEntity<EventoDto> update(@PathVariable Long labId, @PathVariable Long eventoId, @Valid @RequestBody UpdateEventoDto dto){
        EventoDto response = eventoService.update(labId, eventoId, dto);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @DeleteMapping("/{labId}/{eventoId}")
    public ResponseEntity<Void> delete(@PathVariable Long eventoId, @PathVariable Long labId){
        eventoService.delete(eventoId, labId);
        return ResponseEntity.noContent().build();
    }
}
