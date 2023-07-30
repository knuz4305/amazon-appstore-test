package com.stefanini.eval.amazonappstorebackend.repository;

import java.util.List;
import java.util.Optional;

import com.stefanini.eval.amazonappstorebackend.model.Comentario;

public interface ComentarioRepository {
    Comentario creaComentario(Comentario comentario);
    Comentario actualizaComentario(Integer idComentario, Comentario comentario);
    void eliminaComentario(Integer idComentario);
    Optional<Comentario> buscaComentarioPorId(Integer idComentario);
    List<Comentario> comentarios();
    List<Comentario> comentariosByIdAplicacion(Integer idAplicacion);
}
