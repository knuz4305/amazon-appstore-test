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

import com.stefanini.eval.amazonappstorebackend.model.Usuario;
import com.stefanini.eval.amazonappstorebackend.repository.UsuarioRepository;
import com.stefanini.eval.amazonappstorebackend.repository.mapper.UsuarioMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository{
    
    private static final String SELECT_ALL = "SELECT ID_USUARIO, CORREO_ELECTRONICO, NOMBRE, APELLIDO, ESTADO FROM USUARIO";
    private static final String SELECT_BY_ID = "SELECT ID_USUARIO, CORREO_ELECTRONICO, NOMBRE, APELLIDO, ESTADO FROM USUARIO WHERE ID_USUARIO =?;";
    private static final String INSERT = "INSERT INTO USUARIO (CORREO_ELECTRONICO, NOMBRE, APELLIDO, ESTADO) VALUES (?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE USUARIO SET CORREO_ELECTRONICO =?, NOMBRE =?, APELLIDO =?, ESTADO =? WHERE ID_USUARIO =?;";
    private static final String DELETE = "DELETE FROM USUARIO WHERE ID_USUARIO =?;";

    private final JdbcTemplate jdbcTemplate;

    public UsuarioRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Usuario actualizaUsuario(Integer idUsuario, Usuario usuario) {
        try {
            jdbcTemplate.update(UPDATE,
                                usuario.getCorreoElectronico(),
                                usuario.getNombre(),
                                usuario.getApellido(),
                                usuario.getEstado(),
                                idUsuario
                                );
            return usuario;
        } catch (Exception e) {
            log.error("Ha ocurrido el siguiente error: ", e.getMessage());
            return Usuario.builder().build();
        }
    }

    @Override
    public Optional<Usuario> buscarUsuarioPorId(Integer idUsuario) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_BY_ID, new UsuarioMapper(), idUsuario));
        } catch (Exception e) {
            log.error("Ha ocurrido el siguiente error: ", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Usuario creaUsuario(Usuario usuario) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            PreparedStatementCreator creator = connection -> {
                PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, usuario.getCorreoElectronico());
                statement.setString(2, usuario.getNombre());
                statement.setString(3, usuario.getApellido());
                statement.setInt(4, usuario.getEstado());
                return statement;
            };
            jdbcTemplate.update(creator, keyHolder);
            Integer idUsuario = (Integer) keyHolder.getKeyList().get(0).get("ID_USUARIO");
            usuario.setIdUsuario(idUsuario);
            return usuario;
        } catch (Exception e) {
            log.error("Ha ocurrido el siguiente error: {}", e.getMessage());
            return Usuario.builder().build();
        }
    }

    @Override
    public void eliminaUsuario(Integer idUsuario) {
        try {
            jdbcTemplate.update(DELETE, idUsuario);
        } catch (Exception e) {
            log.error("Ha ocurrido el siguiente error: ", e.getMessage());
        }
    }

    @Override
    public List<Usuario> usuarios() {
        try {
            return jdbcTemplate.query(SELECT_ALL, new UsuarioMapper());
        } catch (Exception e) {
            log.error("Ha ocurrido el siguiente error: ", e.getMessage());
            return new ArrayList<>();
        }
    }
    
}
