package com.coagronet.kardexItem.repositories;

import com.coagronet.kardexItem.KardexItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KardexItemRepository extends JpaRepository<KardexItem, Integer> {

    KardexItem findByIdAndEstadoNot(Integer id, Integer estado);

    Page<KardexItem> findByEstadoNot(Integer estado, Pageable pageable);

}
