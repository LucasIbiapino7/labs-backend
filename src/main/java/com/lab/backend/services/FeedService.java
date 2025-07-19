package com.lab.backend.services;

import com.lab.backend.dtos.feed.FeedItemDto;
import com.lab.backend.model.Evento;
import com.lab.backend.model.Post;
import com.lab.backend.repositories.EventoRepository;
import com.lab.backend.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class FeedService {

    private static final int TARGET = 30;
    private static final int EVENTS_MAX = 15;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private EventoRepository eventoRepository;

    public List<FeedItemDto> feedGlobal() {
        List<Evento> nextEvents = eventoRepository.findNextEvents(PageRequest.of(0, EVENTS_MAX));
        int falta = TARGET - nextEvents.size();
        List<Post> recentPosts = postRepository.findRecentPosts(PageRequest.of(0, falta));
        List<FeedItemDto> feed = new ArrayList<>(TARGET);
        nextEvents.forEach(x -> feed.add(toDto(x)));
        recentPosts.forEach(x -> feed.add(toDto(x)));
        feed.sort(Comparator.comparing(FeedItemDto::getInstante).reversed());
        return feed;
    }

    private FeedItemDto toDto(Evento e) {
        return new FeedItemDto(
                e.getId(), "EVENTO",
                e.getTitulo(),
                e.getDescricao(),
                e.getDataEvento(),
                e.getLaboratorio().getId(),
                e.getLaboratorio().getNome(),
                e.getLaboratorio().getGradientAccent().name().toLowerCase(),
                e.getProfile().getId(),
                e.getProfile().getNome(),
                e.getProfile().getPhotoUrl());
    }

    private FeedItemDto toDto(Post p) {
        return new FeedItemDto(
                p.getId(), "POST",
                p.getTitulo(),
                p.getConteudo(),
                p.getInstante(),
                p.getLaboratorio().getId(),
                p.getLaboratorio().getNome(),
                p.getLaboratorio().getGradientAccent().name().toLowerCase(),
                p.getProfile().getId(),
                p.getProfile().getNome(),
                p.getProfile().getPhotoUrl());
    }
}
