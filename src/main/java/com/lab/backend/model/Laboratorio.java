package com.lab.backend.model;

import jakarta.persistence.*;

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
}
