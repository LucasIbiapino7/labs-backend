package com.lab.backend.dtos.posts;

import com.lab.backend.dtos.lab.LabMinDto;
import com.lab.backend.dtos.profile.ProfileMinDto;
import com.lab.backend.model.Laboratorio;
import com.lab.backend.model.Post;
import com.lab.backend.model.Profile;
import com.lab.backend.model.enums.Visibilidade;

import java.time.LocalDateTime;

public class PostDto {
    private Long id;
    private String titulo;
    private String conteudo;
    private LocalDateTime instante;
    private Visibilidade visibilidade;
    private ProfileMinDto autor;
    private LabMinDto laboratorio;

    public PostDto() {
    }

    public PostDto(Post entityPost, Profile entityProfile, Laboratorio entityLab) {
        id = entityPost.getId();
        titulo = entityPost.getTitulo();
        conteudo = entityPost.getConteudo();
        instante = entityPost.getInstante();
        visibilidade = entityPost.getVisibilidade();
        autor = new ProfileMinDto(entityProfile);
        laboratorio = new LabMinDto(entityLab);
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

    public ProfileMinDto getAutor() {
        return autor;
    }

    public void setAutor(ProfileMinDto autor) {
        this.autor = autor;
    }

    public LabMinDto getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(LabMinDto laboratorio) {
        this.laboratorio = laboratorio;
    }
}
