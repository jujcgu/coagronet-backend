package com.coagronet.productoCategoria.mappers;

import org.springframework.stereotype.Component;

import com.coagronet.productoCategoria.ProductoCategoria;
import com.coagronet.productoCategoria.dtos.ProductoCategoriaDTO;

@Component
public class ProductoCategoriaMapper {
    
    public ProductoCategoriaDTO toDto(ProductoCategoria productoCategoria) {
        ProductoCategoriaDTO dto = new ProductoCategoriaDTO();
        dto.setId(productoCategoria.getId());
        dto.setNombre(productoCategoria.getNombre());
        dto.setDescripcion(productoCategoria.getDescripcion());
        dto.setEstado(productoCategoria.getEstado());
        return dto;
    }

    public ProductoCategoria toEntity(ProductoCategoriaDTO dto) {
        ProductoCategoria productoCategoria = new ProductoCategoria();
        productoCategoria.setId(dto.getId());
        productoCategoria.setNombre(dto.getNombre());
        productoCategoria.setDescripcion(dto.getDescripcion());
        productoCategoria.setEstado(dto.getEstado());
        return productoCategoria;
    }
}
