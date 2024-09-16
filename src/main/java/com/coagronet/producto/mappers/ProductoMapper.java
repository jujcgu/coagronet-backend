package com.coagronet.producto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import com.coagronet.producto.Producto;
import com.coagronet.producto.dtos.ProductoDTO;

@Component
@Mapper(componentModel = "spring")
public interface ProductoMapper {

    ProductoMapper INSTANCE = Mappers.getMapper(ProductoMapper.class);

    @Mapping(source = "productoCategoria.id", target = "productoCategoriaId")
    ProductoDTO toDto(Producto producto);

    @Mapping(source = "productoCategoriaId", target = "productoCategoria.id")
    Producto toEntity(ProductoDTO productoDTO);
}
