package com.lab.backend.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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
    @Column(name = "banner_gradient")
    private String bannerGradient;
    @Column(name = "logo_url")
    private String logoUrl;
    @OneToMany(mappedBy = "laboratorio")
    private List<Material> materiais = new ArrayList<>();
    @OneToMany(mappedBy = "laboratorio")
    private List<Evento> eventos = new ArrayList<>();
    @OneToMany(mappedBy = "laboratorio")
    private List<Post> posts = new ArrayList<>();
}
