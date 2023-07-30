package com.stefanini.eval.amazonappstorebackend.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.stefanini.eval.amazonappstorebackend.model.Usuario;

public class UsuarioMapper implements RowMapper<Usuario>{

    @Override
    @Nullable
    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Usuario
                    .builder()
                    .idUsuario(rs.getInt("ID_USUARIO"))
                    .correoElectronico(rs.getString("CORREO_ELECTRONICO"))
                    .nombre(rs.getString("NOMBRE"))
                    .apellido(rs.getString("APELLIDO"))
                    .estado(rs.getInt("ESTADO"))
                    .build();
    }
    
}
