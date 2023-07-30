package com.stefanini.eval.amazonappstorebackend.service;

import java.util.List;

import com.stefanini.eval.amazonappstorebackend.model.Aplicacion;

public interface AplicacionService {
    Aplicacion crear(Aplicacion aplicacion);
    Aplicacion actualizar(Integer idAplicacion, Aplicacion aplicacion);
    void eliminar(Integer idAplicacion);
    Aplicacion buscarPorId(Integer idAplicacion);
    List<Aplicacion> aplicaciones();
    
}
