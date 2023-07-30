package com.stefanini.eval.amazonappstorebackend.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comentario {
    
    private Integer idComentario;
    private Integer idAplicacion;
    private Integer idUsuario;
    private String descripcion;
    private Date fechaRegistro;
    private Integer estado;
    private Integer calificacion;
    
}
