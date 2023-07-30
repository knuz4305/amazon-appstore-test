package com.stefanini.eval.amazonappstorebackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    
    private Integer idUsuario;
    private String correoElectronico;
    private String nombre;
    private String apellido;
    private Integer estado;
    
}
