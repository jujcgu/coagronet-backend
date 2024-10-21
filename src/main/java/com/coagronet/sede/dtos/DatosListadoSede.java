package com.coagronet.sede.dtos;

import com.coagronet.sede.Sede;

public record DatosListadoSede(Long id, String nombre) {
    public DatosListadoSede(Sede sede) {
        this(sede.getId(), sede.getNombre());
    }
}
