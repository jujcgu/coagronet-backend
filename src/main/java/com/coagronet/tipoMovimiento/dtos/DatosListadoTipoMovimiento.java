package com.coagronet.tipoMovimiento.dtos;

import com.coagronet.tipoMovimiento.TipoMovimiento;

public record DatosListadoTipoMovimiento(Integer id, String nombre) {
    public DatosListadoTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this(tipoMovimiento.getId(), tipoMovimiento.getNombre());
    }
}
