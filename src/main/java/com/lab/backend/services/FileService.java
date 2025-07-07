package com.lab.backend.services;

import com.lab.backend.dtos.files.FileUploadDto;
import com.lab.backend.model.Profile;
import com.lab.backend.repositories.ProfileRepository;
import com.lab.backend.services.exceptions.FileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Component
public class FileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AuthService authService;

    public FileUploadDto uploadProfile(MultipartFile file, Path fileStorageLocation) {
        if (file.isEmpty()) {
            throw new FileException("O arquivo esta vazio!");
        }
        try {
            // cria a pasta caso não exista
            Files.createDirectories(fileStorageLocation);

            // gera nome para o arquivo
            String ext = StringUtils.cleanPath(file.getOriginalFilename());
            String filename = UUID.randomUUID() + "." + ext;

            Path target = fileStorageLocation.resolve(filename);
            file.transferTo(target); // copia no disco

            // monta URL de download
            String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/files/download/")
                    .path(filename)
                    .toUriString();

            // gravar o caminho relativo na entidade do banco
            Profile profile = authService.getOrCreateProfile();
            profile.setPhotoUrl(filename);
            profileRepository.save(profile); // Na entidade, salavamos o caminho relativo (filename)

            return new FileUploadDto(filename, downloadUri);
        } catch (Exception ex){
           throw new FileException("Erro ao salvar o arquivo");
        }
    }
}
