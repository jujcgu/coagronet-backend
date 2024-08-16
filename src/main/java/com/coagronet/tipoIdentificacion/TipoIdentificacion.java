package com.coagronet.tipoIdentificacion;

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
@Table(name = "tipo_identificacion")
public class TipoIdentificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tii_id")
    private Integer id;

    @Column(name = "tii_nombre")
    private String nombre;

    @Column(name = "tii_descripcion")
    private String descripcion;

    @Column(name = "tii_estado")
    private Integer estado;

}
