package com.lab.backend.model;

import com.lab.backend.model.enums.GradientAccent;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "tb_laboratorio")
public class Laboratorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(name = "descricao_curta", columnDefinition = "TEXT")
    private String descricaoCurta;
    @Column(name = "descricao_longa", columnDefinition = "TEXT")
    private String descricaoLonga;
    @Enumerated(EnumType.STRING)
    @Column(name = "gradient_accent")
    private GradientAccent gradientAccent;
    @Column(name = "logo_url")
    private String logoUrl;
    @OneToMany(mappedBy = "laboratorio")
    private List<Material> materiais = new ArrayList<>();
    @OneToMany(mappedBy = "laboratorio")
    private List<Evento> eventos = new ArrayList<>();
    @OneToMany(mappedBy = "laboratorio")
    private List<Post> posts = new ArrayList<>();
    @OneToMany(mappedBy = "id.laboratorio")
    private Set<ProfileLaboratorio> profileLaboratorios = new HashSet<>();

    public Laboratorio() {
    }

    public Laboratorio(Long id, String nome, String descricaoCurta, String descricaoLonga, GradientAccent gradientAccent, String logoUrl) {
        this.id = id;
        this.nome = nome;
        this.descricaoCurta = descricaoCurta;
        this.descricaoLonga = descricaoLonga;
        this.gradientAccent = gradientAccent;
        this.logoUrl = logoUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricaoCurta() {
        return descricaoCurta;
    }

    public void setDescricaoCurta(String descricaoCurta) {
        this.descricaoCurta = descricaoCurta;
    }

    public String getDescricaoLonga() {
        return descricaoLonga;
    }

    public void setDescricaoLonga(String descricaoLonga) {
        this.descricaoLonga = descricaoLonga;
    }

    public GradientAccent getGradientAccent() {
        return gradientAccent;
    }

    public void setGradientAccent(GradientAccent gradientAccent) {
        this.gradientAccent = gradientAccent;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public List<Material> getMateriais() {
        return materiais;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Set<ProfileLaboratorio> getProfileLaboratorios() {
        return profileLaboratorios;
    }

    public List<Profile> getUsers(){
        return profileLaboratorios.stream().map(x -> x.getProfile()).toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Laboratorio that = (Laboratorio) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
