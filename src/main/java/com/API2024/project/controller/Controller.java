package com.API2024.project.controller;

import com.API2024.project.model.UsuariosModel;
import com.API2024.project.repository.UsuarioRepository;
import com.API2024.project.service.ServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Controller {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ServiceUsuario serviceUsuario;

    @GetMapping("/all")
      public ResponseEntity<List<UsuariosModel>> getAll(){
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuariosModel> getById(@PathVariable Long id){
        return usuarioRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuariosModel> postUsuario(@RequestBody UsuariosModel usuariosModel){
        try {
            return serviceUsuario.cadastrarUsuario(usuariosModel)
                    .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
                    .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
        } catch (Exception e) {
            // Log da exceção
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/atualizar")
    public ResponseEntity<UsuariosModel> putUsuario(@RequestBody UsuariosModel usuariosModel) {
        try {
            return serviceUsuario.atualizarUsuario(usuariosModel)
                    .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            // Log da exceção
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
