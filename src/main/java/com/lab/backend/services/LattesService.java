package com.lab.backend.services;

import com.lab.backend.dtos.lattes.*;
import com.lab.backend.model.Profile;
import com.lab.backend.model.enums.ProfileType;
import com.lab.backend.model.pesquisas.Orientacao;
import com.lab.backend.model.pesquisas.Patente;
import com.lab.backend.model.pesquisas.Premio;
import com.lab.backend.model.pesquisas.Publicacao;
import com.lab.backend.repositories.*;
import com.lab.backend.services.client.LattesClient;
import com.lab.backend.utils.LattesMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class LattesService {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private PublicacaoRepository publicacaoRepository;
    @Autowired
    private OrientacaoRepository orientacaoRepository;
    @Autowired
    private PremioRepository premioRepository;
    @Autowired
    private PatenteRepository patenteRepository;
    @Autowired
    private LattesClient lattesClient;
    @Autowired
    private LattesMapper lattesMapper;

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(LattesService.class);

    // @Scheduled(cron = "0 0 3 */7 * ?")
    @Scheduled(
            initialDelayString = "PT5M", // 5 minutos após o start
            fixedRateString = "PT24H",// depois, repete a cada 24 horas
            zone = "America/Fortaleza"
    )
    public void run(){
        log.info("Iniciando sincronização de publicações Lattes…");

        List<Profile> professores = profileRepository.findAllByProfileTypeAndIdLattesIsNotNull(ProfileType.PROFESSOR);
        if (professores.isEmpty()) {
            log.info("[LattesService] nenhum professor com idLattes → encerrou");
            return;
        }

        process(professores);

        log.info("[LattesService] sincronização concluída com sucesso!");
    }

    void process(List<Profile> list){
        List<String> ids = list.stream()
                .map(Profile::getIdLattes)
                .filter(StringUtils::hasText)
                .toList();

        if (ids.isEmpty()) {
            return;
        }

        Map<String, LattesProfileDto> batch = lattesClient.getPesquisas(ids);

        list.forEach(profile -> {
            String id = profile.getIdLattes();
            LattesProfileDto dto = batch.get(id);
            if (dto == null) {
                return;
            }
            persistirProducoes(profile, dto.getProducoes());
            persistirOrientacoes(profile, dto.getOrientacoes());
            persistirPremios(profile, dto.getPremios());
            persistirPatentes(profile, dto.getPatentes());
        });
    }

    private void persistirPatentes(Profile profile, List<PatenteDto> patentes) {
        for (PatenteDto dto : patentes) {
            patenteRepository.findByExternalId(dto.getId())
                    .orElseGet(() -> {
                        Patente nova = lattesMapper.toPatente(dto, profile);
                        nova.setAuthor(profile);
                        return patenteRepository.save(nova);
                    });
        }
    }

    private void persistirPremios(Profile profile, List<PremioDto> premios) {
        for (PremioDto dto : premios) {
            premioRepository.findByExternalId(dto.getId())
                    .orElseGet(() -> {
                        Premio nova = lattesMapper.toPremio(dto, profile);
                        nova.setAuthor(profile);
                        return premioRepository.save(nova);
                    });
        }
    }

    private void persistirOrientacoes(Profile profile, List<OrientacaoDto> orientacoes) {
        for (OrientacaoDto dto : orientacoes) {
            orientacaoRepository.findByExternalId(dto.getId())
                    .orElseGet(() -> {
                        Orientacao nova = lattesMapper.toOrientacao(dto, profile);
                        nova.setAuthor(profile);
                        return orientacaoRepository.save(nova);
                    });
        }
    }

    private void persistirProducoes(Profile profile, List<ProducaoDto> producoes) {
        for (ProducaoDto dto : producoes) {
            publicacaoRepository.findByExternalId(dto.getId())
                    .orElseGet(() -> {
                        Publicacao nova = lattesMapper.toPublicacao(dto, profile);
                        nova.setAuthor(profile);
                        return publicacaoRepository.save(nova);
                    });
        }
    }


}
