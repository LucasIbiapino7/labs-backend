package com.lab.backend.controllers;

import com.lab.backend.dtos.feed.FeedItemDto;
import com.lab.backend.services.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/feed")
public class FeedController {

    @Autowired
    private FeedService feedService;

    @GetMapping
    public ResponseEntity<List<FeedItemDto>> feedGlobal(){
        List<FeedItemDto> response = feedService.feedGlobal();
        return ResponseEntity.ok(response);
    }
}
