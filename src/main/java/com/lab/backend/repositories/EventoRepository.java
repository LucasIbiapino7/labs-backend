package com.lab.backend.repositories;

import com.lab.backend.dtos.eventos.EventoDto;
import com.lab.backend.model.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    @Query("""
       select new com.lab.backend.dtos.eventos.EventoDto(e, lab, autor)
       from Evento e
       join e.profile autor
       join e.laboratorio lab
       where lab.id = :labId
       order by e.dataEvento desc
""")
    Page<EventoDto> findByLab(Long labId, Pageable pageable);

    @Query("""
        select obj
        from Evento obj
        where obj.laboratorio.id = :labId
          and obj.dataEvento >= CURRENT_TIMESTAMP
        order by obj.dataEvento asc
    """)
    List<Evento> getNextEvents(@Param("labId") Long labId);

    @Query("""
           select obj
           from Evento obj
           where obj.dataEvento >= CURRENT_TIMESTAMP
           order by obj.dataEvento asc
       """)
    List<Evento> findNextEvents(Pageable limit15);
}
