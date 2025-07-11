package com.lab.backend.dtos.profile;

public class AuthorFeedDto {
    private Long   id;
    private String nome;
    private String photoUrl;

    public AuthorFeedDto() {
    }

    public AuthorFeedDto(Long id, String nome, String photoUrl) {
        this.id = id;
        this.nome = nome;
        this.photoUrl = photoUrl;
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
