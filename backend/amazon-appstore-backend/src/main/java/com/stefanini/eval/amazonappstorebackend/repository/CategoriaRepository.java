package com.stefanini.eval.amazonappstorebackend.repository;

import java.util.List;
import java.util.Optional;

import com.stefanini.eval.amazonappstorebackend.model.Categoria;

public interface CategoriaRepository {
    Categoria creaCategoria(Categoria categoria);
    Categoria actualizaCategoria(Integer idCategoria, Categoria categoria);
    void eliminaCategoria(Integer idCategoria);
    Optional<Categoria> buscaCategoriaPorId(Integer idCategoria);
    List<Categoria> categorias();
}
