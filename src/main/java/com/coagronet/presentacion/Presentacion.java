package com.coagronet.presentacion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "presentacion")
public class Presentacion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "presentacion_generator")
    @SequenceGenerator(name = "presentacion_generator", sequenceName = "presentacion_pre_id_seq", allocationSize = 1)
    @Column(name = "pre_id")
    private Long id;

    @Column(name = "pre_nombre", length = 255)
    private String nombre;

    @Column(name = "pre_descripcion", length = 500)
    private String descripcion;

    @Column(name = "pre_estado", columnDefinition = "integer default 1")
    private Integer estado;

}
