package com.stefanini.eval.amazonappstorebackend.repository.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.stefanini.eval.amazonappstorebackend.model.Aplicacion;
import com.stefanini.eval.amazonappstorebackend.repository.AplicacionRepository;
import com.stefanini.eval.amazonappstorebackend.repository.mapper.AplicacionMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class AplicacionRepositoryImpl implements AplicacionRepository{

    private static final String SELECT_ALL = "SELECT ID_APLICACION, NOMBRE, DESCRIPCION, COSTO, CALIFICACION_ACTUAL, ID_CATEGORIA, VERSION_APP, RUTA_IMAGEN_PREVIA, RUTA_IMAGEN_ICONO FROM APLICACION;";
    private static final String SELECT_BY_ID = "SELECT ID_APLICACION, NOMBRE, DESCRIPCION, COSTO, CALIFICACION_ACTUAL, ID_CATEGORIA, VERSION_APP, RUTA_IMAGEN_PREVIA, RUTA_IMAGEN_ICONO FROM APLICACION WHERE ID_APLICACION =?;";
    private static final String INSERT = "INSERT INTO APLICACION  (NOMBRE, DESCRIPCION, COSTO, CALIFICACION_ACTUAL, ID_CATEGORIA, VERSION, RUTA_IMAGEN_PREVIA, RUTA_IMAGEN_ICONO) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE APLICACION  SET NOMBRE=?, DESCRIPCION =?, COSTO =?, CALIFICACION_ACTUAL = ?, ID_CATEGORIA = ?, VERSION = ?, RUTA_IMAGEN_PREVIA =?, RUTA_IMAGEN_ICONO=? WHERE ID_APLICACION =?;";
    private static final String DELETE = "DELETE FROM APLICACION WHERE ID_APLICACION =?;";

    private final JdbcTemplate jdbcTemplate;    
    
    public AplicacionRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Aplicacion actualizaAplicacion(Integer idAplicacion, Aplicacion aplicacion) {
        try {
            jdbcTemplate.update(UPDATE,
                                aplicacion.getNombre(),
                                aplicacion.getDescripcion(),
                                aplicacion.getCosto(),
                                aplicacion.getCalificacionActual(),
                                aplicacion.getIdCategoria(),
                                aplicacion.getVersion(),
                                aplicacion.getRutaImagenPrevia(),
                                aplicacion.getRutaImagenIcono()
                                );
            return aplicacion;
        } catch (Exception e) {
            log.error("Ha ocurrido el siguiente error: ", e.getMessage());
            return Aplicacion.builder().build();
        }
    }

    @Override
    public List<Aplicacion> aplicaciones() {
        try {
            return jdbcTemplate.query(SELECT_ALL, new AplicacionMapper());
        } catch (Exception e) {
            log.error("Ha ocurrido el siguiente error: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Aplicacion> buscaAplicacionPorId(Integer idAplicacion) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_BY_ID, new AplicacionMapper(), idAplicacion));
        } catch (Exception e) {
            log.error("Ha ocurrido el siguiente error: ", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Aplicacion creaAplicacion(Aplicacion aplicacion) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            PreparedStatementCreator creator = connection -> {
                PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, aplicacion.getNombre());
                statement.setString(2, aplicacion.getDescripcion());
                statement.setDouble(3, aplicacion.getCosto());
                statement.setInt(4, aplicacion.getCalificacionActual());
                statement.setInt(5, aplicacion.getIdCategoria());
                statement.setString(6, aplicacion.getVersion());
                statement.setString(7, aplicacion.getRutaImagenPrevia());
                statement.setString(8, aplicacion.getRutaImagenIcono());
                return statement;
            };
            jdbcTemplate.update(creator, keyHolder);
            Integer idAplicacion = (Integer) keyHolder.getKeyList().get(0).get("ID_APLICACION");
            aplicacion.setIdAplicacion(idAplicacion);
            return aplicacion;
        } catch (Exception e) {
            log.error("Ha ocurrido el siguiente error: ", e.getMessage());
            return Aplicacion.builder().build();
        }
    }

    @Override
    public void eliminarAplicacion(Integer idAplicacion) {
        try {
            jdbcTemplate.update(DELETE, idAplicacion);    
        } catch (Exception e) {
            log.error("Ha ocurrido el siguiente error: ", e.getMessage());
        }
    }
    
}
