package com.stefanini.eval.amazonappstorebackend.service;

import java.util.List;

import com.stefanini.eval.amazonappstorebackend.model.Comentario;

public interface ComentarioService {
    Comentario crear(Comentario comentario);
    Comentario actualizar(Integer idComentario, Comentario comentario);
    void eliminar(Integer idComentario);
    Comentario buscarPorId(Integer idComentario);
    List<Comentario> comentarios();
    List<Comentario> comentariosByAplicacion(Integer idAplicacion);
}
