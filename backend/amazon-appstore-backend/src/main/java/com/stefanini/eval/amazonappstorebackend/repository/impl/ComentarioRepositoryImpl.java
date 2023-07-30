package com.stefanini.eval.amazonappstorebackend.repository.impl;

import java.sql.Date;
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

import com.stefanini.eval.amazonappstorebackend.model.Comentario;
import com.stefanini.eval.amazonappstorebackend.repository.ComentarioRepository;
import com.stefanini.eval.amazonappstorebackend.repository.mapper.ComentarioMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ComentarioRepositoryImpl implements ComentarioRepository{

    private static final String SELECT_ALL = "SELECT ID_COMENTARIO, ID_APLICACION, ID_USUARIO, DESCRIPCION, FECHA_REGISTRO, ESTADO, CALIFICACION FROM COMENTARIO";
    private static final String SELECT_BY_ID = "SELECT ID_COMENTARIO, ID_APLICACION, ID_USUARIO, DESCRIPCION, FECHA_REGISTRO, ESTADO, CALIFICACION FROM COMENTARIO WHERE ID_COMENTARIO =?;";
    private static final String INSERT = "INSERT INTO COMENTARIO (ID_APLICACION, ID_USUARIO, DESCRIPCION, FECHA_REGISTRO, ESTADO, CALIFICACION ) VALUES (?,?,?,?,?,?);";
    private static final String UPDATE = "UPDATE COMENTARIO SET DESCRIPCION =?, ESTADO=?, CALIFICACION =? WHERE ID_COMENTARIO =? AND ID_APLICACION =? AND ID_USUARIO =?;";
    private static final String DELETE = "DELETE FROM COMENTARIO WHERE ID_COMENTARIO =? AND ID_APLICACION =? AND ID_USUARIO =?;";
    
    private final JdbcTemplate jdbcTemplate;

    public ComentarioRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Comentario actualizaComentario(Integer idComentario, Comentario comentario) {
        try {
            jdbcTemplate.update(UPDATE,
                                comentario.getDescripcion(),
                                comentario.getEstado(),
                                comentario.getCalificacion(),
                                comentario.getIdComentario(),
                                comentario.getIdAplicacion(),
                                comentario.getIdUsuario(),
                                comentario.getCalificacion()
                                );
            return comentario;
        } catch (Exception e) {
            log.error("Ha ocurrido el siguiente error: ", e.getMessage());
            return Comentario.builder().build();
        }
    }

    @Override
    public Optional<Comentario> buscaComentarioPorId(Integer idComentario) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_BY_ID, new ComentarioMapper(), idComentario));
        } catch (Exception e) {
            log.error("Ha ocurrido el siguiente error: ", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Comentario> comentarios() {
        try {
            return jdbcTemplate.query(SELECT_ALL, new ComentarioMapper());
        } catch (Exception e) {
            log.error("Ha ocurrido el siguiente error: ", e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Comentario creaComentario(Comentario comentario) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            PreparedStatementCreator creator = connection -> {
                PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, comentario.getIdAplicacion());
                statement.setInt(2, comentario.getIdUsuario());
                statement.setString(3, comentario.getDescripcion());
                statement.setDate(4, new Date(new java.util.Date().getTime()));
                statement.setInt(6, comentario.getEstado());
                statement.setInt(7, comentario.getCalificacion());
                return statement;
            };
            jdbcTemplate.update(creator, keyHolder);
            Integer idComentario = (Integer) keyHolder.getKeyList().get(0).get("ID_COMENTARIO");
            comentario.setIdComentario(idComentario);
            return comentario;
        } catch (Exception e) {
            log.error("Ha ocurrido el siguiente error: ", e.getMessage());
            return Comentario.builder().build();
        }
    }

    @Override
    public void eliminaComentario(Integer idComentario) {
        try {
            jdbcTemplate.update(DELETE, idComentario);
        } catch (Exception e) {
            log.error("Ha ocurrido el siguiente error: ", e.getMessage());
        }
    }

        

}
