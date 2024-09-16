package com.coagronet.unidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "unidad")
public class Unidad {

    @Id
    @GeneratedValue
    @Column(name = "uni_id")
    private Integer id;

    @Column(name = "uni_nombre", length = 255)
    private String nombre;

    @Column(name = "uni_descripcion", length = 500)
    private String descripcion;

    @Column(name = "uni_estado", columnDefinition = "integer default 1")
    private Integer estado;

}
