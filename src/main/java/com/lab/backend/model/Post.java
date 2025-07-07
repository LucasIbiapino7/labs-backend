package com.lab.backend.model;

import com.lab.backend.model.enums.Visibilidade;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Column(columnDefinition = "TEXT")
    private String conteudo;
    private LocalDateTime intante;
    @Enumerated(EnumType.STRING)
    private Visibilidade visibilidade;
}
