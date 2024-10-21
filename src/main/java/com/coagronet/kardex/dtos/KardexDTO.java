package com.coagronet.kardex.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class KardexDTO {
    private Integer id;
    private LocalDateTime fechaHora;
    private Integer almacenID;
    private Integer produccionID;
    private Integer tipoMovimientoID;
    private String descripcion;
    private Integer estado;
}
