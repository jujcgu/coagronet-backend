package com.coagronet.presentacion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "presentacion")
public class Presentacion {

    @Id
    @GeneratedValue
    @Column(name = "pre_id")
    private Long id;

    @Column(name = "pre_nombre", length = 255)
    private String nombre;

    @Column(name = "pre_descripcion", length = 500)
    private String descripcion;

    @Column(name = "pre_estado", columnDefinition = "integer default 1")
    private Integer estado;

}
