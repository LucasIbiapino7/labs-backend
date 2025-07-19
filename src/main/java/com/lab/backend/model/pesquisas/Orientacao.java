package com.lab.backend.model.pesquisas;

import com.lab.backend.model.Profile;
import com.lab.backend.model.enums.OrientacaoNivel;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_orientacao")
public class Orientacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile author;
    //@Enumerated(EnumType.STRING)
    private String nivel;
    @Column(nullable = false)
    private String titulo;
    private Integer ano;
    private String discente;
    private String instituicao;
    @Column(name = "external_id", unique = true, nullable = false)
    private Integer externalId;

    public Orientacao() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Profile getAuthor() {
        return author;
    }

    public void setAuthor(Profile author) {
        this.author = author;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getDiscente() {
        return discente;
    }

    public void setDiscente(String discente) {
        this.discente = discente;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public Integer getExternalId() {
        return externalId;
    }

    public void setExternalId(Integer externalId) {
        this.externalId = externalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orientacao that = (Orientacao) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
