package com.lab.backend.dtos.lattes;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class PatenteDto {
    private Integer id;
    private String tipo;
    private String titulo;
    @JsonFormat(pattern = "ddMMyyyy")
    private LocalDate dataDeposito;
    private String codigo;

    public PatenteDto() {
    }

    public Integer getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDate getDataDeposito() {
        return dataDeposito;
    }

    public String getCodigo() {
        return codigo;
    }
}
