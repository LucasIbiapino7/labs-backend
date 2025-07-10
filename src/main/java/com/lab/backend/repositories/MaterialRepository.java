package com.lab.backend.repositories;

import com.lab.backend.dtos.eventos.EventoDto;
import com.lab.backend.dtos.materiais.MaterialDto;
import com.lab.backend.model.Material;
import com.lab.backend.model.enums.MaterialType;
import com.lab.backend.model.enums.Visibilidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {

    @Query("""
   select new com.lab.backend.dtos.materiais.MaterialDto(obj)
   from Material obj
   where obj.laboratorio.id = :labId
     and obj.visibilidade in :visibilidade
     and (:nome = '' or upper(obj.titulo) like upper(concat(:nome, '%')))
     and (:tipo is null or obj.tipo = :tipo)
   order by obj.titulo asc
""")
    Page<MaterialDto> findByLabAndFilters(@Param("labId") Long labId,
                                          @Param("visibilidade") Collection<Visibilidade> visibilidade,
                                          @Param("nome") String nome,
                                          @Param("tipo") MaterialType tipo,
                                          Pageable pageable);

    @Query("""
       select new com.lab.backend.dtos.materiais.MaterialDto(obj)
       from Material obj
       join obj.laboratorio lab
       where lab.id = :labId
         and obj.visibilidade in :visibilidade
       order by obj.titulo asc
""")
    Page<MaterialDto> findByLabAndVisibilidade(Long labId, List<Visibilidade> visibilidade, Pageable pageable);
}
