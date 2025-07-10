package com.lab.backend.services;

import com.lab.backend.dtos.eventos.EventoDto;
import com.lab.backend.dtos.eventos.EventoInsertDto;
import com.lab.backend.dtos.eventos.EventoMinDto;
import com.lab.backend.dtos.eventos.UpdateEventoDto;
import com.lab.backend.model.Evento;
import com.lab.backend.model.Laboratorio;
import com.lab.backend.model.Profile;
import com.lab.backend.model.enums.LabRole;
import com.lab.backend.repositories.EventoRepository;
import com.lab.backend.repositories.LaboratorioRepository;
import com.lab.backend.repositories.ProfileLaboratorioRepository;
import com.lab.backend.services.exceptions.DatabaseException;
import com.lab.backend.services.exceptions.ForbiddenException;
import com.lab.backend.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventoService {

    @Autowired
    private AuthService authService;
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private ProfileLaboratorioRepository profileLaboratorioRepository;
    @Autowired
    private LaboratorioRepository laboratorioRepository;

    @Transactional(readOnly = true)
    public Page<EventoDto> feedEventosLab(Long labId, Pageable pageable) {
        Laboratorio laboratorio = laboratorioRepository.findById(labId).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));
        return eventoRepository.findByLab(labId, pageable);
    }

    @Transactional
    public EventoDto insert(Long labId, EventoInsertDto dto) {
        Laboratorio laboratorio = laboratorioRepository.findById(labId).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));
        Profile profile = authService.getOrCreateProfile();
        boolean labAdmin = profileLaboratorioRepository.existsByIdLaboratorioIdAndIdProfileIdAndLabRoleIn(
                labId,
                profile.getId(),
                List.of(LabRole.ADMIN, LabRole.OWNER));
        if (!labAdmin){
            throw new ForbiddenException("Você não tem acesso!");
        }
        Evento evento = new Evento();
        evento.setTitulo(dto.getTitulo());
        evento.setDescricao(dto.getDescricao());
        evento.setDataEvento(dto.getDataEvento());
        evento.setInstante(LocalDateTime.now());
        evento.setLocal(dto.getLocal());
        evento.setProfile(profile);
        evento.setLaboratorio(laboratorio);
        evento = eventoRepository.save(evento);
        return new EventoDto(evento, laboratorio, profile);
    }

    @Transactional
    public EventoDto update(Long labId, Long eventoId, UpdateEventoDto dto) {
        Laboratorio laboratorio = laboratorioRepository.findById(labId).orElseThrow(
                () -> new ResourceNotFoundException("Laboratorio não encontrado!"));
        Evento evento = eventoRepository.findById(eventoId).orElseThrow(
                () -> new ResourceNotFoundException("Evento não encontrado!"));
        Profile profile = authService.getOrCreateProfile();
        boolean labAdmin = profileLaboratorioRepository.existsByIdLaboratorioIdAndIdProfileIdAndLabRoleIn(
                labId,
                profile.getId(),
                List.of(LabRole.ADMIN, LabRole.OWNER));
        if (!labAdmin){
            throw new ForbiddenException("Você não tem acesso!");
        }
        evento.setTitulo(dto.getTitulo());
        evento.setDescricao(dto.getDescricao());
        evento.setLocal(dto.getLocal());
        evento.setDataEvento(dto.getDataEvento());
        evento = eventoRepository.save(evento);
        return new EventoDto(evento, laboratorio, profile);
    }

    public void delete(Long eventoId, Long labId) {
        Profile profile = authService.getOrCreateProfile();
        boolean labAdmin = profileLaboratorioRepository.existsByIdLaboratorioIdAndIdProfileIdAndLabRoleIn(
                labId,
                profile.getId(),
                List.of(LabRole.ADMIN, LabRole.OWNER));
        if (!labAdmin){
            throw new ForbiddenException("Você não tem acesso!");
        }
        if (!eventoRepository.existsById(eventoId)){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            eventoRepository.deleteById(eventoId);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    @Transactional(readOnly = true)
    public List<EventoMinDto> getNextEvents(Long labId) {
        List<Evento> result = eventoRepository.getNextEvents(labId);
        return result.stream().map(EventoMinDto::new).toList();
    }
}
