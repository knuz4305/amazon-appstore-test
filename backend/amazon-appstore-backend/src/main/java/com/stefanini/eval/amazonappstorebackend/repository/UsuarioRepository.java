package com.stefanini.eval.amazonappstorebackend.repository;

import java.util.List;
import java.util.Optional;

import com.stefanini.eval.amazonappstorebackend.model.Usuario;

public interface UsuarioRepository {
    Usuario creaUsuario(Usuario usuario);
    Usuario actualizaUsuario(Integer idUsuario, Usuario usuario);
    void eliminaUsuario(Integer idUsuario);
    Optional<Usuario> buscarUsuarioPorId(Integer idUsuario);
    List<Usuario> usuarios();
}
