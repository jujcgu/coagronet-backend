package com.coagronet.productoPresentacion.dtos;

import lombok.Data;

@Data
public class ProductoPresentacionDTO {

    private Integer id;
    private Integer productoId;
    private String nombre;
    private Integer unidadId;
    private String descripcion;
    private Integer estado;
    private Double cantidad;
    private Integer marcaId;
    private Integer presentacionId;
}
