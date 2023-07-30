package com.stefanini.eval.amazonappstorebackend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.stefanini.eval.amazonappstorebackend.model.Categoria;
import com.stefanini.eval.amazonappstorebackend.repository.CategoriaRepository;
import com.stefanini.eval.amazonappstorebackend.service.CategoriaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoriaServiceImpl implements CategoriaService{
    
    private final CategoriaRepository repository;

    public CategoriaServiceImpl(CategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Categoria actualizar(Integer idCategoria, Categoria categoria) {
        try {
            Optional<Categoria> categoriaDB = repository.buscaCategoriaPorId(idCategoria);
            if(!categoriaDB.isPresent()){
                return Categoria.builder().build();
            }
            return repository.actualizaCategoria(idCategoria, categoria);
        } catch (Exception e) {
            log.error("Ha ocurrido un error al intentar actualizar el registro.  {}", e.getMessage());
            return Categoria.builder().build();
        }
    }

    @Override
    public Categoria buscarPorId(Integer idCategoria) {
        try {
            Optional<Categoria> categoriaDB = repository.buscaCategoriaPorId(idCategoria);
            if(!categoriaDB.isPresent()){
                return Categoria.builder().build();
            }
            return categoriaDB.get();
        } catch (Exception e) {
            log.error("Ha ocurrido un error al intentar buscar el registro.   {}", e.getMessage());
            return Categoria.builder().build();
        }
    }

    @Override
    public List<Categoria> categorias() {
        try {
            return repository.categorias();
        } catch (Exception e) {
            log.error("Ha ocurrido un error al intentar recuperar los registros.  {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Categoria crear(Categoria categoria) {
        try {
            return repository.creaCategoria(categoria);            
        } catch (Exception e) {
            log.error("Error al registrar información, verifique los datos proporcionados.   {}", e.getMessage());
            return Categoria.builder().build();
        }
    }

    @Override
    public void eliminar(Integer idCategoria) {
        try {
            Optional<Categoria> categoria = repository.buscaCategoriaPorId(idCategoria);
            if(!categoria.isPresent()){
                log.error("No fue posible ubicar el registro.");
            }
            repository.eliminaCategoria(idCategoria);
        } catch (Exception e) {
            log.error("Ha ocurrido el siguiente error:  {}",e.getMessage());
        }
        
    }

    
}
