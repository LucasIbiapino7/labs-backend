package com.lab.backend.dtos.posts;

import com.lab.backend.model.enums.Visibilidade;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class PostInsertDto {
    private String titulo;
    @NotBlank(message = "campo requerido!")
    private String conteudo;
    private LocalDateTime instante;
    private Visibilidade visibilidade;

    public PostInsertDto() {
    }

    public PostInsertDto(String titulo, String conteudo, LocalDateTime instante, Visibilidade visibilidade) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.instante = instante;
        this.visibilidade = visibilidade;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public void setInstante(LocalDateTime instante) {
        this.instante = instante;
    }

    public Visibilidade getVisibilidade() {
        return visibilidade;
    }

    public void setVisibilidade(Visibilidade visibilidade) {
        this.visibilidade = visibilidade;
    }
}
