package com.coagronet.produccion.dtos;

import com.coagronet.produccion.Produccion;

public record DatosListadoProduccion(Integer id, String nombre) {
    public DatosListadoProduccion(Produccion produccion) {
        this(produccion.getId(), produccion.getNombre());
    }
}
