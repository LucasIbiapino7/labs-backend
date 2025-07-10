package com.lab.backend.dtos.eventos;

import com.lab.backend.dtos.lab.LabMinDto;
import com.lab.backend.dtos.profile.ProfileMinDto;
import com.lab.backend.model.Evento;
import com.lab.backend.model.Laboratorio;
import com.lab.backend.model.Profile;

import java.time.LocalDateTime;

public class EventoDto {
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataEvento;
    private LocalDateTime instante;
    private String local;
    private LabMinDto lab;
    private ProfileMinDto author;

    public EventoDto() {
    }

    public EventoDto(Evento entityEvento, Laboratorio entityLaboratorio, Profile entityProfile) {
        id = entityEvento.getId();
        titulo = entityEvento.getTitulo();
        descricao = entityEvento.getDescricao();
        dataEvento = entityEvento.getDataEvento();
        instante = entityEvento.getInstante();
        local = entityEvento.getLocal();
        lab = new LabMinDto(entityLaboratorio);
        author = new ProfileMinDto(entityProfile);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDateTime dataEvento) {
        this.dataEvento = dataEvento;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public void setInstante(LocalDateTime instante) {
        this.instante = instante;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public LabMinDto getLab() {
        return lab;
    }

    public void setLab(LabMinDto lab) {
        this.lab = lab;
    }

    public ProfileMinDto getAuthor() {
        return author;
    }

    public void setAuthor(ProfileMinDto author) {
        this.author = author;
    }
}
