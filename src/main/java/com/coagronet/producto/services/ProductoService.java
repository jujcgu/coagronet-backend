package com.coagronet.producto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.coagronet.producto.Producto;
import com.coagronet.producto.dtos.ProductoDTO;
import com.coagronet.producto.mappers.ProductoMapper;
import com.coagronet.producto.repositories.ProductoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoMapper productoMapper;

    public ProductoDTO getProductoById(Integer id) {
        Producto producto = productoRepository.findByIdAndEstado(id, 1)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        return productoMapper.toDto(producto);
    }

    public Page<Producto> getAllProductos(Pageable pageable) {
        return productoRepository.findByEstadoNot(2, pageable);
    }

    public ProductoDTO createProducto(ProductoDTO productoDTO) {
        Producto producto = productoMapper.toEntity(productoDTO);
        Producto savedProducto = productoRepository.save(producto);
        return productoMapper.toDto(savedProducto);
    }

    public ProductoDTO updateProducto(Integer id, ProductoDTO productoDTO) {
        Producto existingProducto = productoRepository.findByIdAndEstado(id, 1)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        Producto updatedProducto = productoMapper.toEntity(productoDTO);
        updatedProducto.setId(existingProducto.getId());
        Producto savedProducto = productoRepository.save(updatedProducto);
        return productoMapper.toDto(savedProducto);
    }

    public void deleteProducto(Integer id) {
        Producto existingProducto = productoRepository.findByIdAndEstado(id, 1)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        existingProducto.setEstado(2); // Cambiar estado a 2 en lugar de eliminar
        productoRepository.save(existingProducto);
    }
}
