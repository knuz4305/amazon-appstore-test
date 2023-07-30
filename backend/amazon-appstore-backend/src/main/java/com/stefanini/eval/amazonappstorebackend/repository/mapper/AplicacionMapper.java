package com.stefanini.eval.amazonappstorebackend.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.stefanini.eval.amazonappstorebackend.model.Aplicacion;

public class AplicacionMapper implements RowMapper<Aplicacion>{

    @Override
    @Nullable
    public Aplicacion mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Aplicacion
                        .builder()
                        .idAplicacion(rs.getInt("ID_APLICACION"))
                        .nombre(rs.getString("NOMBRE"))
                        .descripcion(rs.getString("DESCRIPCION"))
                        .costo(rs.getDouble("COSTO"))
                        .calificacionActual(rs.getInt("CALIFICACION_ACTUAL"))
                        .idCategoria(rs.getInt("ID_CATEGORIA"))
                        .version(rs.getString("VERSION_APP"))
                        .rutaImagenPrevia(rs.getString("RUTA_IMAGEN_PREVIA"))
                        .rutaImagenIcono(rs.getString("RUTA_IMAGEN_ICON"))
                        .build();
    }
    
}
