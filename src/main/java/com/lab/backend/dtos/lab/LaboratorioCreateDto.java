package com.lab.backend.dtos.lab;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LaboratorioCreateDto {
    @NotBlank(message = "Campo Requerido")
    @Size(min = 3, max = 30, message = "O campo deve conter entre 3 e 30 caracteres!")
    private String nome;

    public LaboratorioCreateDto() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
