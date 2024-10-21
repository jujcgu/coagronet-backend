package com.coagronet.almacen;

import com.coagronet.estado.Estado;
import com.coagronet.sede.Sede;
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
@Table(name = "almacen")
public class Almacen {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "almacen_generator")
    @SequenceGenerator(name = "almacen_generator", sequenceName = "almacen_alm_id_seq", allocationSize = 1)
    @Column(name = "alm_id", nullable = false)
    private Integer id;

    @Column(name = "alm_nombre", length = 100)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "alm_sede_id", referencedColumnName = "sed_id")
    private Sede sede;

    @Column(name = "alm_geolocalizacion", columnDefinition = "json")
    private String geolocalizacion;

    @Column(name = "alm_coordenadas", columnDefinition = "json")
    private String coordenadas;

    @Column(name = "alm_descripcion", length = 255)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "alm_estado", referencedColumnName = "est_id")
    private Estado estado;
}
