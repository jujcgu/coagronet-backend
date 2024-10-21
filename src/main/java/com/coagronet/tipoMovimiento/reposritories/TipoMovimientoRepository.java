package com.coagronet.tipoMovimiento.reposritories;

import com.coagronet.empresa.Empresa;
import com.coagronet.tipoMovimiento.TipoMovimiento;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoMovimientoRepository extends JpaRepository<TipoMovimiento, Integer> {
    List<TipoMovimiento> findByEstadoNot(Integer estado, Sort sort);
}
