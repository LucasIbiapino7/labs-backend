package com.lab.backend.model;

import com.lab.backend.model.enums.ProfileType;
import jakarta.persistence.*;

import java.util.*;

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
    @Column(name = "id_lattes")
    private String idLattes;
    @Column(name = "photo_url")
    private String photoUrl;
    @Enumerated(EnumType.STRING)
    @Column(name = "profile_type")
    private ProfileType profileType;
    @OneToMany(mappedBy = "profile")
    private List<Evento> eventos = new ArrayList<>();
    @OneToMany(mappedBy = "profile")
    private List<Post> posts = new ArrayList<>();
    @OneToMany(mappedBy = "id.profile")
    private Set<ProfileLaboratorio> profileLaboratorios = new HashSet<>();

    public Profile() {
    }

    public Profile(Long id, String nome, String sub, String bio, String linkLattes, String linkGithub, String linkLinkedin, String idLattes, String photoUrl, ProfileType profileType) {
        this.id = id;
        this.nome = nome;
        this.sub = sub;
        this.bio = bio;
        this.linkLattes = linkLattes;
        this.linkGithub = linkGithub;
        this.linkLinkedin = linkLinkedin;
        this.idLattes = idLattes;
        this.photoUrl = photoUrl;
        this.profileType = profileType;
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

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
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

    public String getIdLattes() {
        return idLattes;
    }

    public void setIdLattes(String idLattes) {
        this.idLattes = idLattes;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setProfileLaboratorios(Set<ProfileLaboratorio> profileLaboratorios) {
        this.profileLaboratorios = profileLaboratorios;
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

    public List<Evento> getEventos() {
        return eventos;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Set<ProfileLaboratorio> getProfileLaboratorios() {
        return profileLaboratorios;
    }

    public List<Laboratorio> getMyLabs(){
        return profileLaboratorios.stream().map(x -> x.getLaboratorio()).toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        return Objects.equals(id, profile.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sub='" + sub + '\'' +
                ", bio='" + bio + '\'' +
                ", linkLattes='" + linkLattes + '\'' +
                ", linkGithub='" + linkGithub + '\'' +
                ", linkLinkedin='" + linkLinkedin + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                '}';
    }
}
