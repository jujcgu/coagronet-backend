package com.coagronet.unidad;

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
@Entity(name = "unidad")
public class Unidad {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unidad_generator")
    @SequenceGenerator(name = "unidad_generator", sequenceName = "unidad_uni_id_seq", allocationSize = 1)
    @Column(name = "uni_id")
    private Integer id;

    @Column(name = "uni_nombre", length = 255)
    private String nombre;

    @Column(name = "uni_descripcion", length = 500)
    private String descripcion;

    @Column(name = "uni_estado", columnDefinition = "integer default 1")
    private Integer estado;

}
