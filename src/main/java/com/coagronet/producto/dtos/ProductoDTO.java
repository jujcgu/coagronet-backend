package com.coagronet.producto.dtos;

import lombok.Data;

@Data
public class ProductoDTO {

    private Integer id;
    private String nombre;
    private Long productoCategoriaId;
    private String descripcion;
    private Integer estado;

}
