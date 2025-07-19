package com.lab.backend.repositories;

import com.lab.backend.dtos.profile.LabMemberAdminDto;
import com.lab.backend.dtos.profile.ProfileMemberDto;
import com.lab.backend.model.Profile;
import com.lab.backend.model.ProfileLaboratorio;
import com.lab.backend.model.ProfileLaboratorioPK;
import com.lab.backend.model.enums.LabRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProfileLaboratorioRepository extends JpaRepository<ProfileLaboratorio, ProfileLaboratorioPK> {

    boolean existsByIdLaboratorioIdAndIdProfileIdAndLabRoleIn(Long labId, Long profileId, Collection<LabRole> roles);

    boolean existsByIdLaboratorioIdAndIdProfileIdAndAdminTrue(Long labId, Long profileId);

    @Query("""
       select obj.labRole
       from ProfileLaboratorio obj
       where obj.id.laboratorio.id = :labId
         and obj.id.profile.id = :profileId
   """)
    Optional<LabRole> findRole(Long labId, Long profileId);

    @Query("""
       select new com.lab.backend.dtos.profile.ProfileMemberDto(
          p.id,
          p.nome,
          p.bio,
          p.linkLattes,
          p.linkGithub,
          p.linkLinkedin,
          p.photoUrl,
          p.profileType,
          obj.ativo
       )
       from ProfileLaboratorio obj
       join obj.id.profile p
       where obj.id.laboratorio.id = :labId
         and upper(p.nome) like upper(concat(:nome, '%'))
       order by
         obj.ativo desc,
         case p.profileType
           when com.lab.backend.model.enums.ProfileType.PROFESSOR then 0
           else 1
         end,
         p.nome
   """)
    Page<ProfileMemberDto> getAllMembers(Long labId, String nome, Pageable pageable);

    @Query("""
        select obj
        from Profile obj
        where upper(obj.nome) like upper(concat(:nome, '%'))
          and not exists (
              select 1
              from ProfileLaboratorio pl
              where pl.id.laboratorio.id = :labId
                and pl.id.profile.id = obj.id
          )
        order by obj.nome
    """)
    Page<Profile> findCandidatesForLab(Long labId, String nome, Pageable pageable);

    @Query("""
    select new com.lab.backend.dtos.profile.LabMemberAdminDto(
        p.id,
        p.nome,
        p.bio,
        p.linkLattes,
        p.linkGithub,
        p.linkLinkedin,
        p.photoUrl,
        p.profileType,
        pl.labRole,
        pl.ativo
    )
    from ProfileLaboratorio pl
      join pl.id.profile p
    where pl.id.laboratorio.id = :labId
      and upper(p.nome) like upper(concat(:nome, '%'))
    order by
      pl.ativo desc,
      case pl.labRole
         when com.lab.backend.model.enums.LabRole.OWNER then 0
         when com.lab.backend.model.enums.LabRole.ADMIN then 1
         else 2
      end,
      case p.profileType
         when com.lab.backend.model.enums.ProfileType.PROFESSOR then 0
         else 1
      end,
      p.nome
""")
    Page<LabMemberAdminDto> findAdminViewMembers(Long labId, String nome, Pageable pageable);

    @Query("""
   select count(pl)
   from ProfileLaboratorio pl
   where pl.id.laboratorio.id = :labId
     and pl.labRole = com.lab.backend.model.enums.LabRole.ADMIN
     and pl.ativo = true
""")
    long countActiveAdmins(Long labId);
    @Query("""
    select pl
    from ProfileLaboratorio pl
    where pl.id.laboratorio.id = :labId
      and pl.labRole = com.lab.backend.model.enums.LabRole.OWNER
""")
    List<ProfileLaboratorio> findOwners(Long labId);
}

