package com.lab.backend.dtos.profile;

import com.lab.backend.model.Profile;

public class ProfileMinDto {
    private Long id;
    private String nome;
    private String bio;
    private String linkLattes;
    private String linkGithub;
    private String linkLinkedin;
    private String photoUrl;

    public ProfileMinDto() {
    }

    public ProfileMinDto(Profile entity) {
        id = entity.getId();
        nome = entity.getNome();
        bio = entity.getBio();
        linkLattes = entity.getLinkLattes();
        linkGithub = entity.getLinkGithub();
        linkLinkedin = entity.getLinkLinkedin();
        photoUrl = entity.getPhotoUrl();
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLinkLattes() {
        return linkLattes;
    }

    public void setLinkLattes(String linkLattes) {
        this.linkLattes = linkLattes;
    }

    public String getLinkGithub() {
        return linkGithub;
    }

    public void setLinkGithub(String linkGithub) {
        this.linkGithub = linkGithub;
    }

    public String getLinkLinkedin() {
        return linkLinkedin;
    }

    public void setLinkLinkedin(String linkLinkedin) {
        this.linkLinkedin = linkLinkedin;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
