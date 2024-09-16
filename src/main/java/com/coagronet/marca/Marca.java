package com.coagronet.marca;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "marca")
public class Marca {

    @Id
    @GeneratedValue
    @Column(name = "mar_id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "mar_nombre", length = 100)
    private String nombre;

    @Column(name = "mar_descripcion", length = 255)
    private String descripcion;

    @Column(name = "mar_estado", columnDefinition = "integer default 1")
    private Integer estado;

}
