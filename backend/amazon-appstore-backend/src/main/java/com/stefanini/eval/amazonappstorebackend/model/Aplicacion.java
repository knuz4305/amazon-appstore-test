package com.stefanini.eval.amazonappstorebackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Aplicacion {
    
    private Integer idAplicacion;
    private String nombre;
    private String descripcion;
    private Double costo;
    private Integer calificacionActual;
    private Integer idCategoria;
    private String version;
    private String rutaImagenPrevia;
    private String rutaImagenIcono;
    
}
