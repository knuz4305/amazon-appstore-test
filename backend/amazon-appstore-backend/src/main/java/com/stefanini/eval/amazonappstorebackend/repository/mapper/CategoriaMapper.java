package com.stefanini.eval.amazonappstorebackend.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.stefanini.eval.amazonappstorebackend.model.Categoria;

public class CategoriaMapper implements RowMapper<Categoria>{

    @Override
    @Nullable
    public Categoria mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Categoria.builder()
                        .idCategoria(rs.getInt("ID_CATEGORIA"))
                        .nombre(rs.getString("NOMBRE"))
                        .descripcion(rs.getString("DESCRIPCION"))
                        .estado(rs.getInt("ESTADO"))
                        .build();
    }
    
}
