package com.stefanini.eval.amazonappstorebackend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.stefanini.eval.amazonappstorebackend.model.Comentario;
import com.stefanini.eval.amazonappstorebackend.repository.ComentarioRepository;
import com.stefanini.eval.amazonappstorebackend.service.ComentarioService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ComentarioServiceImpl implements ComentarioService{
    
    private final ComentarioRepository repository;

    public ComentarioServiceImpl(ComentarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Comentario actualizar(Integer idComentario, Comentario comentario) {
        try {
            Optional<Comentario> comentarioDB = repository.buscaComentarioPorId(idComentario);
            if(!comentarioDB.isPresent()){
                return Comentario.builder().build();
            }
            return repository.actualizaComentario(idComentario, comentario);
        } catch (Exception e) {
            log.error("Ha ocurrido un error al intentar actualizar el registro.  {}", e.getMessage());
            return Comentario.builder().build();
        }
    }

    @Override
    public Comentario buscarPorId(Integer idComentario) {
        try {
            Optional<Comentario> comentarioDB = repository.buscaComentarioPorId(idComentario);
            if(!comentarioDB.isPresent()){
                return Comentario.builder().build();
            }
            return comentarioDB.get();
        } catch (Exception e) {
            log.error("Ha ocurrido un error al intentar buscar el registro.   {}", e.getMessage());
            return Comentario.builder().build();
        }
    }

    @Override
    public List<Comentario> comentarios() {
        try {
            return repository.comentarios();
        } catch (Exception e) {
            log.error("Ha ocurrido un error al intentar recuperar los registros.  {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Comentario> comentariosByAplicacion(Integer idAplicacion) {
        try {
            return repository.comentariosByIdAplicacion(idAplicacion);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Comentario crear(Comentario comentario) {
        try {
            return repository.creaComentario(comentario);
        } catch (Exception e) {
            log.error("Error al registrar información, verifique los datos proporcionados.   {}", e.getMessage());
            return Comentario.builder().build();
        }
    }

    @Override
    public void eliminar(Integer idComentario) {
        try {
            Optional<Comentario> comentario = repository.buscaComentarioPorId(idComentario);
            if(!comentario.isPresent()){
                log.error("No fue posible ubicar el registro.");
            }
            repository.eliminaComentario(idComentario);
        } catch (Exception e) {
            log.error("Ha ocurrido el siguiente error:  {}",e.getMessage());
        }
    }
    
    

}
