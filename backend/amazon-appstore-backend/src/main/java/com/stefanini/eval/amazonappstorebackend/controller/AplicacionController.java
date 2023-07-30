package com.stefanini.eval.amazonappstorebackend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stefanini.eval.amazonappstorebackend.model.Aplicacion;
import com.stefanini.eval.amazonappstorebackend.service.AplicacionService;

@RestController
@RequestMapping("/v1.0/aplicaciones")
public class AplicacionController {
    
    private final AplicacionService service;

    public AplicacionController(AplicacionService service) {
        this.service = service;
    }
    
    @GetMapping("/")
    public ResponseEntity<List<Aplicacion>> getAllAplicaciones(){
        List<Aplicacion> aplicaciones = service.aplicaciones();
        return ResponseEntity.ok().body(aplicaciones);
    }

    @GetMapping("/{idAplicacion}")
    public ResponseEntity<Aplicacion> getByIdAplicacion(@PathVariable("idAplicacion") Integer idAplicacion){
        Aplicacion aplicacion = service.buscarPorId(idAplicacion);
        if(null!=aplicacion.getIdAplicacion()){
            return ResponseEntity.ok().body(aplicacion);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<Aplicacion> createAplicacion(@RequestBody Aplicacion aplicacion){
        try {
            return ResponseEntity.ok().body(service.crear(aplicacion));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{idAplicacion}")
    public ResponseEntity<Aplicacion> updateAplicacion(@PathVariable("idAplicacion") Integer idAplicacion, @RequestBody Aplicacion aplicacion){
        try {
            return ResponseEntity.ok().body(service.actualizar(idAplicacion, aplicacion));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{idAplicacion}")
    public ResponseEntity<Aplicacion> eliminaAplicacion(@PathVariable("idAplicacion") Integer idAplicacion){
        try {
            service.eliminar(idAplicacion);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
