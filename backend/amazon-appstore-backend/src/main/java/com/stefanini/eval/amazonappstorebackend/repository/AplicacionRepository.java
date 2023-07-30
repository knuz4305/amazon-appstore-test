package com.stefanini.eval.amazonappstorebackend.repository;

import java.util.List;
import java.util.Optional;

import com.stefanini.eval.amazonappstorebackend.model.Aplicacion;

public interface AplicacionRepository {
    Aplicacion creaAplicacion(Aplicacion aplicacion);
    Aplicacion actualizaAplicacion(Integer idAplicacion, Aplicacion aplicacion);
    void eliminarAplicacion(Integer idAplicacion);
    Optional<Aplicacion> buscaAplicacionPorId(Integer idAplicacion);
    List<Aplicacion> aplicaciones();
}
