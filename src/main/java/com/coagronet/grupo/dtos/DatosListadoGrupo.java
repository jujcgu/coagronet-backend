package com.coagronet.grupo.dtos;

import com.coagronet.grupo.Grupo;

public record DatosListadoGrupo(Long id, String nombre) {
    public DatosListadoGrupo(Grupo grupo){
        this(grupo.getId(), grupo.getNombre());
    }
}
