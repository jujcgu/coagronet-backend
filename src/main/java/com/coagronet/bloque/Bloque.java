package com.coagronet.bloque;

import com.coagronet.estado.Estado;
import com.coagronet.sede.Sede;
import com.coagronet.tipoBloque.TipoBloque;
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
@Table(name = "bloque")
public class Bloque {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bloque_generator")
    @SequenceGenerator(name = "bloque_generator", sequenceName = "bloque_blo_id_seq", allocationSize = 1)
    @Column(name = "blo_id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "blo_sede_id", referencedColumnName = "sed_id")
    private Sede sede;

    @ManyToOne
    @JoinColumn(name = "blo_tipo_bloque_id", referencedColumnName = "tib_id")
    private TipoBloque tipoBloque;

    @Column(name = "blo_nombre", length = 100)
    private String nombre;

    @Column(name = "blo_geolocalizacion", columnDefinition = "json")
    private String geolocalizacion;  // Cambia a 'String' si usas JSON como cadena

    @Column(name = "blo_coordenadas", columnDefinition = "json")
    private String coordenadas;  // Cambia a 'String' si usas JSON como cadena

    @Column(name = "blo_numero_pisos")
    private Integer numeroPisos;

    @Column(name = "blo_descripcion", length = 255)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "blo_estado", referencedColumnName = "est_id")
    private Estado estado;

}
