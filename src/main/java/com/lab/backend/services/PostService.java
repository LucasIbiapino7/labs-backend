package com.lab.backend.services;

import com.lab.backend.dtos.posts.PostDto;
import com.lab.backend.dtos.posts.PostInsertDto;
import com.lab.backend.model.Laboratorio;
import com.lab.backend.model.Post;
import com.lab.backend.model.Profile;
import com.lab.backend.model.enums.LabRole;
import com.lab.backend.model.enums.Visibilidade;
import com.lab.backend.repositories.LaboratorioRepository;
import com.lab.backend.repositories.PostRepository;
import com.lab.backend.repositories.ProfileLaboratorioRepository;
import com.lab.backend.services.exceptions.DatabaseException;
import com.lab.backend.services.exceptions.ForbiddenException;
import com.lab.backend.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private AuthService authService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private LaboratorioRepository laboratorioRepository;
    @Autowired
    private ProfileLaboratorioRepository profileLaboratorioRepository;

    @Transactional
    public PostDto insert(Long labId, PostInsertDto dto) {
        Laboratorio laboratorio = laboratorioRepository.findById(labId).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));
        Profile profile = authService.getOrCreateProfile();
        boolean labMember = profileLaboratorioRepository.existsByIdLaboratorioIdAndIdProfileIdAndLabRoleIn(
                labId,
                profile.getId(),
                List.of(LabRole.ADMIN, LabRole.OWNER, LabRole.MEMBER));
        if (!labMember){
            throw new ForbiddenException("Você não tem acesso!");
        }
        Post post = new Post();
        post.setTitulo((dto.getTitulo() != null) ? dto.getTitulo() : "");
        post.setConteudo(dto.getConteudo());
        post.setInstante(LocalDateTime.now());
        post.setVisibilidade(Visibilidade.PUBLICO);
        post.setProfile(profile);
        post.setLaboratorio(laboratorio);
        post = postRepository.save(post);
        return new PostDto(post, profile, laboratorio);
    }

    @Transactional
    public PostDto insertPrivatePost(Long labId, PostInsertDto dto) {
        Laboratorio laboratorio = laboratorioRepository.findById(labId).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));
        Profile profile = authService.getOrCreateProfile();
        boolean labMember = profileLaboratorioRepository.existsByIdLaboratorioIdAndIdProfileIdAndLabRoleIn(
                labId,
                profile.getId(),
                List.of(LabRole.ADMIN, LabRole.OWNER, LabRole.MEMBER));
        if (!labMember){
            throw new ForbiddenException("Você não tem acesso!");
        }
        Post post = new Post();
        post.setTitulo((dto.getTitulo() != null) ? dto.getTitulo() : "");
        post.setConteudo(dto.getConteudo());
        post.setInstante(LocalDateTime.now());
        post.setVisibilidade(Visibilidade.PRIVADO);
        post.setProfile(profile);
        post.setLaboratorio(laboratorio);
        post = postRepository.save(post);
        return new PostDto(post, profile, laboratorio);
    }

    @Transactional(readOnly = true)
    public Page<PostDto> getFeedLab(Long labId, Pageable pageable) {
        Laboratorio laboratorio = laboratorioRepository.findById(labId).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));
        return postRepository.findByLabAndVisibilidade(labId, List.of(Visibilidade.PUBLICO), pageable);
    }

    @Transactional(readOnly = true)
    public Page<PostDto> getFeedLabInterno(Long labId, Pageable pageable, List<Visibilidade> visibilidades) {
        Laboratorio laboratorio = laboratorioRepository.findById(labId).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));
        Profile profile = authService.getOrCreateProfile();
        boolean labMember = profileLaboratorioRepository.existsByIdLaboratorioIdAndIdProfileIdAndLabRoleIn(
                labId,
                profile.getId(),
                List.of(LabRole.ADMIN, LabRole.OWNER, LabRole.MEMBER));
        boolean admin = authService.hasGlobalRole("ROLE_ADMIN");
        if (!labMember && !admin){
            throw new ForbiddenException("Você não tem acesso!");
        }
        if (visibilidades == null || visibilidades.isEmpty()){
            visibilidades = List.of(Visibilidade.PRIVADO);
        }
        return postRepository.findByLabAndVisibilidade(labId, visibilidades, pageable);
    }

    @Transactional
    public void delete(Long postId, Long labId) {
        Profile profile = authService.getOrCreateProfile();
        boolean labAdmin = profileLaboratorioRepository.existsByIdLaboratorioIdAndIdProfileIdAndLabRoleIn(
                labId,
                profile.getId(),
                List.of(LabRole.ADMIN, LabRole.OWNER));
        if (!labAdmin){
            throw new ForbiddenException("Você não tem acesso!");
        }
        if (!postRepository.existsById(postId)){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            postRepository.deleteById(postId);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial");
        }
    }
}
