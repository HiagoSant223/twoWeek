package com.API2024.project.repository;

import com.API2024.project.model.UsuariosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuariosModel, Long> {

    List<UsuariosModel> findAll();

    List<UsuariosModel> findByUsuario(String usuario);
}
