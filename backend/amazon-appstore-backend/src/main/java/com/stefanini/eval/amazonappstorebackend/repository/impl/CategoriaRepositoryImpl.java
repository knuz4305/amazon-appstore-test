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

import com.stefanini.eval.amazonappstorebackend.model.Categoria;
import com.stefanini.eval.amazonappstorebackend.repository.CategoriaRepository;
import com.stefanini.eval.amazonappstorebackend.repository.mapper.CategoriaMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class CategoriaRepositoryImpl implements CategoriaRepository{

    private static final String SELECT_ALL = "SELECT ID_CATEGORIA, NOMBRE, DESCRIPCION, ESTADO FROM CATEGORIA";
    private static final String SELECT_BY_ID = "SELECT ID_CATEGORIA, NOMBRE, DESCRIPCION, ESTADO FROM CATEGORIA WHERE ID_CATEGORIA=?";
    private static final String INSERT = "INSERT INTO CATEGORIA(NOMBRE,DESCRIPCION,ESTADO) VALUES(?, ?, ?);";
    private static final String UPDATE = "UPDATE CATEGORIA SET NOMBRE = ?, DESCRIPCION = ?, ESTADO = ? WHERE ID_CATEGORIA=?;";
    private static final String DELETE = "DELETE FROM CATEGORIA WHERE ID_CATEGORIA=?;";

    private final JdbcTemplate jdbcTemplate;

    public CategoriaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Categoria actualizaCategoria(Integer idCategoria, Categoria categoria) {
        try {
            jdbcTemplate.update(UPDATE, 
                                categoria.getNombre(), 
                                categoria.getDescripcion(), 
                                categoria.getEstado(), 
                                idCategoria);
            return categoria;
        } catch (Exception e) {
            log.error("Ha ocurrido el siguiente error: ", e.getMessage());
            return Categoria.builder().build();
        }
    }

    @Override
    public Optional<Categoria> buscaCategoriaPorId(Integer idCategoria) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_BY_ID, new CategoriaMapper(), idCategoria));
        } catch (Exception e) {
            log.error("Ha ocurrido el siguiente error: ", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Categoria> categorias() {
        try {
            return jdbcTemplate.query(SELECT_ALL, new CategoriaMapper());
        } catch (Exception e) {
            log.error("Ha ocurrido el siguiente error: ", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Categoria creaCategoria(Categoria categoria) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            PreparedStatementCreator creator = connection -> {
                PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, categoria.getNombre());
                statement.setString(2, categoria.getDescripcion());
                statement.setInt(3, categoria.getEstado());
                return statement;
            };
            jdbcTemplate.update(creator, keyHolder);
            Integer idCategoria = (Integer) keyHolder.getKeyList().get(0).get("ID_CATEGORIA");
            categoria.setIdCategoria(idCategoria);
            return categoria;
        } catch (Exception e) {
            log.error("Ha ocurrido el siguiente error: ", e.getMessage());
            return Categoria.builder().build();
        }
    }

    @Override
    public void eliminaCategoria(Integer idCategoria) {
        try {
            jdbcTemplate.update(DELETE, idCategoria);
        } catch (Exception e) {
            log.error("Ha ocurrido el siguiente error: ", e.getMessage());
        }
    }
    
}
