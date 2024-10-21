package com.coagronet.kardex.mappers;

import com.coagronet.kardex.Kardex;
import com.coagronet.kardex.dtos.KardexDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface KardexMapper {

    KardexMapper INSTANCE = Mappers.getMapper(KardexMapper.class);

    @Mapping(source = "almacen.id", target = "almacenID")
    @Mapping(source = "produccion.id", target = "produccionID")
    @Mapping(source = "tipoMovimiento.id", target = "tipoMovimientoID")
    KardexDTO toDto(Kardex kardex);

    @Mapping(source = "almacenID", target = "almacen.id")
    @Mapping(source = "produccionID", target = "produccion.id")
    @Mapping(source = "tipoMovimientoID", target = "tipoMovimiento.id")
    Kardex toEntity(KardexDTO kardexDTO);
}

