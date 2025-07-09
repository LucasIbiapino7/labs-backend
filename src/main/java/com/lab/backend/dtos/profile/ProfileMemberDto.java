package com.lab.backend.dtos.profile;

import com.lab.backend.model.enums.ProfileType;

public class ProfileMemberDto {
    private Long id;
    private String nome;
    private String bio;
    private String linkLattes;
    private String linkGithub;
    private String linkLinkedin;
    private String photoUrl;
    private ProfileType profileType;
    private boolean ativo;

    public ProfileMemberDto() {
    }

    public ProfileMemberDto(Long id, String nome, String bio, String linkLattes, String linkGithub, String linkLinkedin, String photoUrl, ProfileType profileType, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.bio = bio;
        this.linkLattes = linkLattes;
        this.linkGithub = linkGithub;
        this.linkLinkedin = linkLinkedin;
        this.photoUrl = photoUrl;
        this.profileType = profileType;
        this.ativo = ativo;
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

    public ProfileType getProfileType() {
        return profileType;
    }

    public void setProfileType(ProfileType profileType) {
        this.profileType = profileType;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
