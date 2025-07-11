package com.lab.backend.dtos.feed;

import com.lab.backend.dtos.lab.LabMinDto;
import com.lab.backend.dtos.profile.AuthorFeedDto;
import com.lab.backend.dtos.profile.ProfileMinDto;

import java.time.LocalDateTime;

public class FeedItemDto {
    private Long id;
    private String tipo;
    private String titulo;
    private String resumo;
    private LocalDateTime instante;
    private LabMinDto lab;
    private AuthorFeedDto author;

    public FeedItemDto(Long id, String tipo, String titulo, String resumo, LocalDateTime instante, Long labId, String labNome,
                       String accent, Long authorId, String authorNome, String authorPhoto) {
        this.id       = id;
        this.tipo     = tipo;
        this.titulo   = titulo;
        this.resumo   = resumo;
        this.instante = instante;
        this.lab      = new LabMinDto(labId, labNome);
        this.author   = new AuthorFeedDto(authorId, authorNome, authorPhoto);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public void setInstante(LocalDateTime instante) {
        this.instante = instante;
    }

    public LabMinDto getLab() {
        return lab;
    }

    public void setLab(LabMinDto lab) {
        this.lab = lab;
    }

    public AuthorFeedDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorFeedDto author) {
        this.author = author;
    }
}

