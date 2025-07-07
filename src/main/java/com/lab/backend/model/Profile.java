package com.lab.backend.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String sub; // Valor unico
    @Column(columnDefinition = "TEXT")
    private String bio;
    @Column(name = "link_lattes")
    private String linkLattes;
    @Column(name = "link_github")
    private String linkGithub;
    @Column(name = "link_linkedin")
    private String linkLinkedin;
    @Column(name = "photo_url")
    private String photoUrl;
    @OneToMany(mappedBy = "profile")
    private List<Evento> eventos = new ArrayList<>();
    @OneToMany(mappedBy = "profile")
    private List<Post> posts = new ArrayList<>();
}
