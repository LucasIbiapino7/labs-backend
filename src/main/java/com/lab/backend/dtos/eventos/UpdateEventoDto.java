package com.lab.backend.dtos.eventos;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class UpdateEventoDto {
    @NotBlank(message = "campo requerido!")
    private String titulo;
    @NotBlank(message = "campo requerido!")
    private String descricao;
    private LocalDateTime dataEvento;
    @NotBlank(message = "campo requerido!")
    private String local;

    public UpdateEventoDto() {
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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
