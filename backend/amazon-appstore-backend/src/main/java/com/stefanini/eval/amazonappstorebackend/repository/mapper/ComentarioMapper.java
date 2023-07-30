package com.stefanini.eval.amazonappstorebackend.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.stefanini.eval.amazonappstorebackend.model.Comentario;

public class ComentarioMapper implements RowMapper<Comentario>{

    @Override
    @Nullable
    public Comentario mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Comentario
                        .builder()
                        .idComentario(rs.getInt("ID_COMENTARIO"))
                        .idAplicacion(rs.getInt("ID_APLICACION"))
                        .idUsuario(rs.getInt("ID_USUARIO"))
                        .descripcion(rs.getString("DESCRIPCION"))
                        .fechaRegistro(rs.getDate("FECHA_REGISTRO"))
                        .estado(rs.getInt("ESTADO"))
                        .calificacion(rs.getInt("CALIFICACION"))
                        .build();
    }
    
}
