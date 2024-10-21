package com.coagronet.bloque.dtos;

import com.coagronet.bloque.Bloque;

public record DatosListadoBloque(Integer id, String nombre) {
    public DatosListadoBloque(Bloque bloque) {
        this(bloque.getId(), bloque.getNombre());
    }
}
