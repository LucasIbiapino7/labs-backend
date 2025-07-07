package com.lab.backend.services;

import com.lab.backend.dtos.lab.LaboratorioCreateDto;
import com.lab.backend.dtos.lab.LaboratorioInfosDto;
import com.lab.backend.dtos.lab.LaboratorioUpdateDto;
import com.lab.backend.model.Laboratorio;
import com.lab.backend.model.Profile;
import com.lab.backend.model.enums.GradientAccent;
import com.lab.backend.repositories.LaboratorioRepository;
import com.lab.backend.repositories.ProfileLaboratorioRepository;
import com.lab.backend.services.exceptions.DatabaseException;
import com.lab.backend.services.exceptions.ForbiddenException;
import com.lab.backend.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LaboratorioService {

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    @Autowired
    private ProfileLaboratorioRepository profileLaboratorioRepository;

    @Autowired
    private AuthService authService;

    public LaboratorioInfosDto findById(Long id) {
        Profile profile = authService.getOrCreateProfile();
        Laboratorio laboratorio = laboratorioRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));
        // pensar se devo ou não comparar o ID do usuario com o ad dos admins do lab
        return new LaboratorioInfosDto(laboratorio);
    }

    @Transactional
    public LaboratorioInfosDto insert(LaboratorioCreateDto dto) {
        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setNome(dto.getNome());
        laboratorio.setDescricaoCurta("");
        laboratorio.setDescricaoLonga("");
        laboratorio.setLogoUrl("");
        laboratorio.setGradientAccent(GradientAccent.GREEN);
        laboratorio = laboratorioRepository.save(laboratorio);
        return new LaboratorioInfosDto(laboratorio);
    }

    @Transactional
    public LaboratorioInfosDto update(Long id, LaboratorioUpdateDto dto) {
        Profile profile = authService.getOrCreateProfile(); // Pega o user do contexto
        Laboratorio laboratorio = laboratorioRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado!"));

        // verifica se o usuario e admin desse lab
        if (!profileLaboratorioRepository.existsByIdLaboratorioIdAndIdProfileIdAndAdminTrue(laboratorio.getId(), profile.getId())){
            throw new ForbiddenException("Voce não tem acesso!");
        }

        laboratorio.setNome(dto.getNome());
        laboratorio.setDescricaoCurta(dto.getDescricaoCurta());
        laboratorio.setDescricaoLonga(dto.getDescricaoLonga());
        laboratorio.setGradientAccent(dto.getGradientAccent());
        laboratorio = laboratorioRepository.save(laboratorio);
        return new LaboratorioInfosDto(laboratorio);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if (!laboratorioRepository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            laboratorioRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial");
        }
    }
}
