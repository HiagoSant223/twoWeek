package com.API2024.project.service;

import com.API2024.project.model.UsuariosModel;
import com.API2024.project.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.Optional;

@Service
public class ServiceUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<UsuariosModel> cadastrarUsuario(UsuariosModel usuario){

        if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
            return Optional.empty();

        return Optional.of(usuarioRepository.save(usuario));
    };

    public Optional<UsuariosModel> atualizarUsuario(UsuariosModel usuario){
        if (usuarioRepository.findById(usuario.getId()).isPresent()) {
            Optional<UsuariosModel> buscarUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
            if (buscarUsuario.isPresent() && buscarUsuario.get().getId() != usuario.getId())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
            return Optional.ofNullable(usuarioRepository.save(usuario));
        }
        return Optional.empty();
    }
}
