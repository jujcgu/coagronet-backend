package com.coagronet.almacen.dtos;

import com.coagronet.almacen.Almacen;

public record DatosListadoAlmacen(Integer Id, String nombre) {
    public DatosListadoAlmacen(Almacen almacen) {
        this(almacen.getId(), almacen.getNombre());
    }
}
