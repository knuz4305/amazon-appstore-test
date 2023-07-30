package com.stefanini.eval.amazonappstorebackend.service;

import java.util.List;

import com.stefanini.eval.amazonappstorebackend.model.Usuario;

public interface UsuarioService {
    Usuario crear(Usuario usuario);
    Usuario actualizar(Integer idUsuario, Usuario usuario);
    void eliminar(Integer idUsuario);
    Usuario buscarPorId(Integer idUsuario);
    List<Usuario> usuarios();
}
