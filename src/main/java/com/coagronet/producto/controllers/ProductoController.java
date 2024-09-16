package com.coagronet.producto.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coagronet.producto.dtos.ProductoDTO;
import com.coagronet.producto.mappers.ProductoMapper;
import com.coagronet.producto.services.ProductoService;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> getProducto(@PathVariable Integer id) {
        ProductoDTO productoDTO = productoService.getProductoById(id);
        return ResponseEntity.ok(productoDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllProductos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String sortBy) {

        String[] sortParams = sortBy.split(",");
        if (sortParams.length != 2
                || (!sortParams[1].equalsIgnoreCase("asc") && !sortParams[1].equalsIgnoreCase("desc"))) {
            return ResponseEntity.badRequest().body(
                    "El parámetro 'sortBy' debe tener el formato 'campo,dirección', donde dirección es 'asc' o 'desc'.");
        }

        Sort sort = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ProductoDTO> productoPage = productoService
                .getAllProductos(pageable)
                .map(ProductoMapper.INSTANCE::toDto);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> header = new HashMap<>();
        header.put("totalElements", productoPage.getTotalElements());
        header.put("totalPages", productoPage.getTotalPages());
        header.put("size", productoPage.getSize());
        header.put("number", productoPage.getNumber());
        header.put("sort", productoPage.getSort());
        header.put("first", productoPage.isFirst());
        header.put("last", productoPage.isLast());
        header.put("numberOfElements", productoPage.getNumberOfElements());
        header.put("empty", productoPage.isEmpty());

        response.put("header", header);
        response.put("data", productoPage.getContent());

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> createProducto(@RequestBody ProductoDTO productoDTO) {
        ProductoDTO createdProducto = productoService.createProducto(productoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> updateProducto(@PathVariable Integer id, @RequestBody ProductoDTO productoDTO) {
        ProductoDTO updatedProducto = productoService.updateProducto(id, productoDTO);
        return ResponseEntity.ok(updatedProducto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Integer id) {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }
}
