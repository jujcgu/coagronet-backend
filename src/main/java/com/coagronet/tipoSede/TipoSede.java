package com.coagronet.tipoSede;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tipo_sede")
public class TipoSede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tis_id")
    private Integer id;
    @Column(name = "tis_nombre")
    private String nombre;
    @Column(name = "tis_descripcion")
    private String descripcion;
    @Column(name = "tis_estado", columnDefinition = "integer default 1")
    private Integer estado;
}
