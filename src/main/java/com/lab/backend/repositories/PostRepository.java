package com.lab.backend.repositories;

import com.lab.backend.dtos.posts.PostDto;
import com.lab.backend.model.Post;
import com.lab.backend.model.enums.Visibilidade;
import com.lab.backend.projections.FeedItemProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("""
       select new com.lab.backend.dtos.posts.PostDto(p, autor, lab)
       from Post p
       join p.profile autor
       join p.laboratorio lab
       where lab.id = :labId
         and p.visibilidade in :visibilidade
       order by p.instante desc
""")
    Page<PostDto> findByLabAndVisibilidade(Long labId, Collection<Visibilidade> visibilidade, Pageable pageable);

    @Query("""
           select obj
           from Post obj
           where obj.visibilidade = 'PUBLICO'
           order by obj.instante desc
       """)
    List<Post> findRecentPosts(Pageable limitN);
}
