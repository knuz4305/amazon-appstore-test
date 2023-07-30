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

import com.stefanini.eval.amazonappstorebackend.model.Comentario;
import com.stefanini.eval.amazonappstorebackend.service.ComentarioService;

@RestController
@RequestMapping("/v1.0/comentarios")
@CrossOrigin(origins = "*")
public class ComentarioController {
    
    private final ComentarioService service;

    public ComentarioController(ComentarioService service) {
        this.service = service;
    }
    
    @GetMapping("/")
    public ResponseEntity<List<Comentario>> getAllComentarios(){
        List<Comentario> comentarios = service.comentarios();
        return ResponseEntity.ok().body(comentarios);
    }

    @GetMapping("/getComentariosByAplicacion/{idAplicacion}")
    public ResponseEntity<List<Comentario>> getAllComentariosByIdAplicacion(@PathVariable("idAplicacion") Integer idAplicacion){
        List<Comentario> comentarios = service.comentariosByAplicacion(idAplicacion);
        return ResponseEntity.ok().body(comentarios);
    }

    @GetMapping("/{idComentario}")
    public ResponseEntity<Comentario> getByIdComentario(@PathVariable("idComentario") Integer idComentario){
        Comentario comentario = service.buscarPorId(idComentario);
        if(null!=comentario.getIdComentario()){
            return ResponseEntity.ok().body(comentario);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<Comentario> createComentario(@RequestBody Comentario comentario){
        try {
            return ResponseEntity.ok().body(service.crear(comentario));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{idComentario}")
    public ResponseEntity<Comentario> updateComentario(@PathVariable("idComentario") Integer idComentario, Comentario comentario){
        try {
            return ResponseEntity.ok().body(service.actualizar(idComentario, comentario));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{idComentario}")
    public ResponseEntity<String> eliminarComentario(@PathVariable("idComentario") Integer idComentario){
        try {
            service.eliminar(idComentario);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
