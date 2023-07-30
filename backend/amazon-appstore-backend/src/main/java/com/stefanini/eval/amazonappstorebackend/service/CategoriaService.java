package com.stefanini.eval.amazonappstorebackend.service;

import java.util.List;

import com.stefanini.eval.amazonappstorebackend.model.Categoria;

public interface CategoriaService {
    Categoria crear(Categoria categoria);
    Categoria actualizar(Integer idCategoria, Categoria categoria);
    void eliminar(Integer idCategoria);
    Categoria buscarPorId(Integer idCategoria);
    List<Categoria> categorias();
}
