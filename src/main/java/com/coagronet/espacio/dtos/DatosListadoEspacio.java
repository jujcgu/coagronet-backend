package com.coagronet.espacio.dtos;

import com.coagronet.espacio.Espacio;

public record DatosListadoEspacio(Integer id, String nombre) {
    public DatosListadoEspacio(Espacio espacio) {
        this(espacio.getId(), espacio.getNombre());
    }
}
