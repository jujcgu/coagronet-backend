package com.coagronet.estado.dtos;

import com.coagronet.estado.Estado;

public record DatosListadoEstado(Integer id, String nombre) {
    public DatosListadoEstado(Estado estado) {
        this(estado.getId(), estado.getNombre());
    }
}
