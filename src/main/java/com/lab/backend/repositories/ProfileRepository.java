package com.lab.backend.repositories;

import com.lab.backend.model.Profile;
import com.lab.backend.model.ProfileLaboratorio;
import com.lab.backend.model.enums.ProfileType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query("""
        SELECT obj
        FROM Profile obj
        WHERE UPPER(obj.nome) LIKE UPPER(CONCAT(:nome , '%'))
          AND obj.id <> :currentUserId
        """)
    Page<Profile> findByName(@Param("nome") String nome, Long currentUserId, Pageable pageable);


    Optional<Profile> findBySub(@Param("sub") String sub);
    @Query("""
        select obj.id
        from Profile obj
        where obj.sub = :sub
    """)
    Optional<Long> findIdBySub(String sub);

    List<Profile> findAllByProfileTypeAndIdLattesIsNotNull(ProfileType profileType);
}
