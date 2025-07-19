package com.lab.backend.controllers;

import com.lab.backend.config.FileStorageProperties;
import com.lab.backend.dtos.files.FileUploadDto;
import com.lab.backend.services.FileService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/api/files")
public class FileController {
    private final Path fileStorageLocation;
    private FileService fileService;

    public FileController(FileStorageProperties fileStorageProperties, FileService fileService) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        this.fileService = fileService;
    }

    // PROTEGIDA
    @PostMapping("/upload")
    public ResponseEntity<FileUploadDto> uploadProfile(@RequestParam("file") MultipartFile file){
        FileUploadDto response = fileService.uploadProfile(file, fileStorageLocation);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/upload/{labId}")
    public ResponseEntity<FileUploadDto> uploadLab(@RequestParam("file") MultipartFile file, @PathVariable Long labId){
        FileUploadDto response = fileService.uploadLab(file, fileStorageLocation, labId);
        return ResponseEntity.ok(response);
    }

    // PUBLICA
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> download(@PathVariable("fileName") String fileName,
                                             HttpServletRequest request) {
        Path resolved = fileStorageLocation.resolve(fileName).normalize();
        if (!resolved.startsWith(fileStorageLocation)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        try {
            Resource resource = new UrlResource(resolved.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.notFound().build();
            }
            String contentType = request.getServletContext()
                    .getMimeType(resource.getFile().getAbsolutePath());
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
