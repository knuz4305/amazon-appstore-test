package com.stefanini.eval.amazonappstorebackend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.stefanini.eval.amazonappstorebackend.model.Aplicacion;
import com.stefanini.eval.amazonappstorebackend.repository.AplicacionRepository;
import com.stefanini.eval.amazonappstorebackend.service.AplicacionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AplicacionServiceImpl implements AplicacionService{
    
    private final AplicacionRepository repository;

    public AplicacionServiceImpl(AplicacionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Aplicacion actualizar(Integer idAplicacion, Aplicacion aplicacion) {
        try {
             
            Optional<Aplicacion> aplicacionDB = repository.buscaAplicacionPorId(idAplicacion);
            if(!aplicacionDB.isPresent()){
                return Aplicacion.builder().build();
            }
            return repository.actualizaAplicacion(idAplicacion, aplicacion);
        } catch (Exception e) {
            log.error("Ha ocurrido un error al intentar actualizar el registro.  {}", e.getMessage());
            return Aplicacion.builder().build();
        }
    }

    @Override
    public List<Aplicacion> aplicaciones() {
        try {
            return repository.aplicaciones();
        } catch (Exception e) {
            log.error("Ha ocurrido un error al intentar recuperar los registros.  {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Aplicacion buscarPorId(Integer idAplicacion) {
        try {
            Optional<Aplicacion> aplicacionDB = repository.buscaAplicacionPorId(idAplicacion);
            if(!aplicacionDB.isPresent()){
                return Aplicacion.builder().build();
            }
            return aplicacionDB.get();
        } catch (Exception e) {
            log.error("Ha ocurrido un error al intentar buscar el registro.   {}", e.getMessage());
            return Aplicacion.builder().build();
        }
    }

    @Override
    public Aplicacion crear(Aplicacion aplicacion) {
        try {
            return repository.creaAplicacion(aplicacion);
        } catch (Exception e) {
            log.error("Error al registrar información, verifique los datos proporcionados.   {}", e.getMessage());
            return Aplicacion.builder().build();
        }
    }

    @Override
    public void eliminar(Integer idAplicacion) {
        try {
            Optional<Aplicacion> aplicacion = repository.buscaAplicacionPorId(idAplicacion);
            if(!aplicacion.isPresent()){
                log.error("No fue posible ubicar el registro.");
            }
            repository.eliminarAplicacion(idAplicacion);
        } catch (Exception e) {
            log.error("Ha ocurrido el siguiente error:  {}",e.getMessage());
        }
    }
    
    

}
