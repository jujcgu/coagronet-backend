package com.coagronet.estado;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "estado")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estado_generator")
    @SequenceGenerator(name = "estado_generator", sequenceName = "estado_est_id_seq", allocationSize = 1)
    @Column(name = "est_id")
    private Integer id;

    @Column(name = "est_nombre", length = 100)
    private String nombre;

    @Column(name = "est_descripcion", length = 255)
    private String descripcion;

    @Column(name = "est_acronimo", length = 100)
    private String acronimo;

}
