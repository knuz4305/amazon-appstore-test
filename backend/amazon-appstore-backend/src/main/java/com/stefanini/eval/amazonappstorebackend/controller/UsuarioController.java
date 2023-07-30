package com.stefanini.eval.amazonappstorebackend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stefanini.eval.amazonappstorebackend.model.Usuario;
import com.stefanini.eval.amazonappstorebackend.service.UsuarioService;

@RestController
@RequestMapping("/v1.0/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
    
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<Usuario>> getAllUsuarios(){
        List<Usuario> usuarios = service.usuarios();
        return ResponseEntity.ok().body(usuarios);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Usuario> getByIdUsuario(@PathVariable("idUsuario") Integer idUsuario){
        Usuario usuario = service.buscarPorId(idUsuario);
        if(null!=usuario.getIdUsuario()){
            return ResponseEntity.ok().body(usuario);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario){
        try {
            return ResponseEntity.ok().body(service.crear(usuario));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable("idUsuario") Integer idUsuario, @RequestBody Usuario usuario){
        try {
            return ResponseEntity.ok().body(service.actualizar(idUsuario, usuario));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<String> eliminaUsuario(@PathVariable("idUsuario") Integer idUsuario){
        try {
            service.eliminar(idUsuario);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
