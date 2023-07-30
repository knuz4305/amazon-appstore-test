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

import com.stefanini.eval.amazonappstorebackend.model.Categoria;
import com.stefanini.eval.amazonappstorebackend.service.CategoriaService;

@RestController
@RequestMapping("/v1.0/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {
    
    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<List<Categoria>> getAllCategorias(){
        List<Categoria> categorias = service.categorias();
        return ResponseEntity.ok().body(categorias);
    }

    @GetMapping("/{idCategoria}")
    public ResponseEntity<Categoria> getByIdCategoria(@PathVariable("idCategoria") Integer idCategoria){
        Categoria categoria = service.buscarPorId(idCategoria);
        if(null!=categoria.getIdCategoria()){
            return ResponseEntity.ok().body(categoria);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria){
        try {
            return ResponseEntity.ok().body(service.crear(categoria));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        
    }

    @PutMapping("/{idCategoria}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable("idCategoria") Integer idCategoria, @RequestBody Categoria categoria){
        try {
            return ResponseEntity.ok().body(service.actualizar(idCategoria, categoria));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @DeleteMapping("/{idCategoria}")
    public ResponseEntity<String> eliminaCategoria(@PathVariable("idCategoria") Integer idCategoria){
        try {
            service.eliminar(idCategoria);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
