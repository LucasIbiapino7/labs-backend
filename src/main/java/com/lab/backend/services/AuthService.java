package com.lab.backend.services;

import com.lab.backend.model.Profile;
import com.lab.backend.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthService {

    @Autowired
    private ProfileRepository profileRepository;

    public Profile getOrCreateProfile(){
        // A ideia, é criar um método que quando tiver um token, pegue ele e tire as informações dele
        // para verificar se nesse serviço o profile já está salvo no BD. Se sim, retorna o usuário, se não, cria
        // e retorna esse usuario

        // Por enquanto, usamos um mock
        return profileRepository.findById(1L).get();
    }
}
