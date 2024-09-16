package com.coagronet.productoPresentacion.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.coagronet.productoPresentacion.ProductoPresentacion;

public interface ProductoPresentacionRepository extends JpaRepository<ProductoPresentacion, Integer> {
    Optional<ProductoPresentacion> findByIdAndEstado(Integer id, Integer estado);

    Page<ProductoPresentacion> findByEstadoNot(Integer estado, Pageable pageable);

}
