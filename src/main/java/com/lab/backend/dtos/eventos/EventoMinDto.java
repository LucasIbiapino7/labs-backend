package com.lab.backend.dtos.eventos;

import com.lab.backend.model.Evento;

import java.time.LocalDateTime;

public class EventoMinDto {
    private Long id;
    private String titulo;
    private LocalDateTime dataEvento;
    private String local;

    public EventoMinDto() {
    }

    public EventoMinDto(Evento evento) {
        id = evento.getId();
        titulo = evento.getTitulo();
        dataEvento = evento.getDataEvento();
        local = evento.getLocal();
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

    public LocalDateTime getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDateTime dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
