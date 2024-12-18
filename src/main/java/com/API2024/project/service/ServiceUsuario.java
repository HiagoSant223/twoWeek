package com.API2024.project.service;

import com.API2024.project.model.UsuariosModel;
import com.API2024.project.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuariosModel> listaUsuarios(){
        return usuarioRepository.findAll();
    }

    public List<UsuariosModel> usuarios(String usuario){
        return usuarioRepository.findByUsuario(usuario);
    }
}
