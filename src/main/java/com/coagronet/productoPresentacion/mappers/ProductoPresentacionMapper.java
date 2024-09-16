package com.coagronet.productoPresentacion.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.coagronet.productoPresentacion.ProductoPresentacion;
import com.coagronet.productoPresentacion.dtos.ProductoPresentacionDTO;

@Mapper(componentModel = "spring")
public interface ProductoPresentacionMapper {

    ProductoPresentacionMapper INSTANCE = Mappers.getMapper(ProductoPresentacionMapper.class);

    @Mapping(source = "producto.id", target = "productoId")
    @Mapping(source = "unidad.id", target = "unidadId")
    @Mapping(source = "marca.id", target = "marcaId")
    @Mapping(source = "presentacion.id", target = "presentacionId")
    ProductoPresentacionDTO toDto(ProductoPresentacion productoPresentacion);

    @Mapping(source = "productoId", target = "producto.id")
    @Mapping(source = "unidadId", target = "unidad.id")
    @Mapping(source = "marcaId", target = "marca.id")
    @Mapping(source = "presentacionId", target = "presentacion.id")
    ProductoPresentacion toEntity(ProductoPresentacionDTO productoPresentacionDTO);
}
