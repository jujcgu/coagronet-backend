package com.coagronet.productoPresentacion.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coagronet.productoPresentacion.dtos.ProductoPresentacionDTO;
import com.coagronet.productoPresentacion.mappers.ProductoPresentacionMapper;
import com.coagronet.productoPresentacion.services.ProductoPresentacionService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/producto-presentaciones")
public class ProductoPresentacionController {

    @Autowired
    private ProductoPresentacionService productoPresentacionService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductoPresentacionDTO> getProductoPresentacion(@PathVariable Integer id) {
        ProductoPresentacionDTO productoPresentacionDTO = productoPresentacionService.getProductoPresentacionById(id);
        return ResponseEntity.ok(productoPresentacionDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllProductoPresentaciones(
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
        Page<ProductoPresentacionDTO> productoPresentacionPage = productoPresentacionService
                .getAllProductoPresentaciones(pageable)
                .map(ProductoPresentacionMapper.INSTANCE::toDto);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> header = new HashMap<>();
        header.put("totalElements", productoPresentacionPage.getTotalElements());
        header.put("totalPages", productoPresentacionPage.getTotalPages());
        header.put("size", productoPresentacionPage.getSize());
        header.put("number", productoPresentacionPage.getNumber());
        header.put("sort", productoPresentacionPage.getSort());
        header.put("first", productoPresentacionPage.isFirst());
        header.put("last", productoPresentacionPage.isLast());
        header.put("numberOfElements", productoPresentacionPage.getNumberOfElements());
        header.put("empty", productoPresentacionPage.isEmpty());

        response.put("header", header);
        response.put("data", productoPresentacionPage.getContent());

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProductoPresentacionDTO> createProductoPresentacion(
            @RequestBody ProductoPresentacionDTO productoPresentacionDTO) {
        ProductoPresentacionDTO createdProductoPresentacion = productoPresentacionService
                .createProductoPresentacion(productoPresentacionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProductoPresentacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoPresentacionDTO> updateProductoPresentacion(@PathVariable Integer id,
            @RequestBody ProductoPresentacionDTO productoPresentacionDTO) {
        ProductoPresentacionDTO updatedProductoPresentacion = productoPresentacionService.updateProductoPresentacion(id,
                productoPresentacionDTO);
        return ResponseEntity.ok(updatedProductoPresentacion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductoPresentacion(@PathVariable Integer id) {
        productoPresentacionService.deleteProductoPresentacion(id);
        return ResponseEntity.noContent().build();
    }
}
