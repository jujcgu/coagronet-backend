package com.coagronet.producto.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.coagronet.producto.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Optional<Producto> findByIdAndEstado(Integer id, Integer estado);

    Page<Producto> findByEstadoNot(Integer estado, Pageable pageable);
}
