package com.stefanini.eval.amazonappstorebackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {
    
    private Integer idCategoria;
    private String nombre;
    private String descripcion;
    private Integer estado;
    
}
