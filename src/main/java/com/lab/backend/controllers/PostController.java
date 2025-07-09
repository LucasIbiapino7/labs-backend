package com.lab.backend.controllers;

import com.lab.backend.dtos.posts.PostDto;
import com.lab.backend.dtos.posts.PostInsertDto;
import com.lab.backend.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/{labId}")
    public ResponseEntity<PostDto> insert(@Valid @RequestBody PostInsertDto dto, @PathVariable Long labId){
        PostDto response = postService.insert(labId, dto);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/{labId}/private")
    public ResponseEntity<PostDto> insertPrivatePost(@Valid @RequestBody PostInsertDto dto, @PathVariable Long labId){
        PostDto response = postService.insertPrivatePost(labId, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{labId}")
    public ResponseEntity<Page<PostDto>> getFeedLab(@PathVariable Long labId, Pageable pageable){
        Page<PostDto> response = postService.getFeedLab(labId, pageable);
        return ResponseEntity.ok(response);
    }
}
