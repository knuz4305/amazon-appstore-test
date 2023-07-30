package com.stefanini.eval.amazonappstorebackend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.stefanini.eval.amazonappstorebackend.model.Usuario;
import com.stefanini.eval.amazonappstorebackend.repository.UsuarioRepository;
import com.stefanini.eval.amazonappstorebackend.service.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsuarioServiceImpl implements UsuarioService{
    
    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario actualizar(Integer idUsuario, Usuario usuario) {
        try {
            Optional<Usuario> usuarioDB = repository.buscarUsuarioPorId(idUsuario);
            if(!usuarioDB.isPresent()){
                return Usuario.builder().build();
            }
            return repository.actualizaUsuario(idUsuario, usuario);
        } catch (Exception e) {
            return Usuario.builder().build();
        }
    }

    @Override
    public Usuario buscarPorId(Integer idUsuario) {
        try {
            Optional<Usuario> usuarioDB = repository.buscarUsuarioPorId(idUsuario);
            if(!usuarioDB.isPresent()){
                return Usuario.builder().build();
            }
            return usuarioDB.get();
        } catch (Exception e) {
            log.error("Ha ocurrido un error al intentar buscar el registro.   {}", e.getMessage());
            return Usuario.builder().build();
        }
    }

    @Override
    public List<Usuario> usuarios() {
        try {
            return repository.usuarios();
        } catch (Exception e) {
            log.error("Ha ocurrido un error al intentar recuperar los registros.  {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Usuario crear(Usuario usuario) {
        try {
            return repository.creaUsuario(usuario);
        } catch (Exception e) {
            log.error("Error al registrar información, verifique los datos proporcionados.   {}", e.getMessage());
            return Usuario.builder().build();
        }
    }

    @Override
    public void eliminar(Integer idUsuario) {
        try {
            Optional<Usuario> usuario = repository.buscarUsuarioPorId(idUsuario);
            if(!usuario.isPresent()){
                log.error("No fue posible ubicar el registro.");
            }
            repository.eliminaUsuario(idUsuario);
        } catch (Exception e) {
            log.error("Ha ocurrido el siguiente error:  {}",e.getMessage());
        }
    }
       
}
