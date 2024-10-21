package com.coagronet.espacio.repositories;

import com.coagronet.espacio.Espacio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EspacioRepository extends JpaRepository<Espacio, Integer> {

    @Query(value= "select e.* from espacio as e, bloque as b, sede as s\n" +
            "where e.esp_bloque_id = b.blo_id \n" +
            "and b.blo_sede_id = s.sed_id\n" +
            "and e.esp_estado != 2\n" +
            "and s.sed_empresa_id = :empresaId\n" +
            "and e.esp_bloque_id = :bloqueId\n" +
            "order by esp_nombre asc",
            nativeQuery = true)
    List<Espacio> buscarEspacioPorBloque(@Param("bloqueId") Integer bloqueId, @Param("empresaId") Long empresaId);
}
