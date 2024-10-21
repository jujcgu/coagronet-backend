package com.coagronet.espacio;

import com.coagronet.bloque.Bloque;
import com.coagronet.estado.Estado;
import com.coagronet.tipoEspacio.TipoEspacio;
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
@Table(name = "espacio")
public class Espacio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "espacio_generator")
    @SequenceGenerator(name = "espacio_generator", sequenceName = "espacio_esp_id_seq", allocationSize = 1)
    @Column(name = "esp_id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "esp_bloque_id", referencedColumnName = "blo_id")
    private Bloque bloque;

    @ManyToOne
    @JoinColumn(name = "esp_tipo_espacio_id", referencedColumnName = "tie_id")
    private TipoEspacio tipoEspacio;

    @Column(name = "esp_nombre", length = 100)
    private String nombre;

    @Column(name = "esp_geolocalizacion", columnDefinition = "json")
    private String geolocalizacion;

    @Column(name = "esp_coordenadas", columnDefinition = "json")
    private String coordenadas;

    @Column(name = "esp_descripcion", length = 255)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "esp_estado", referencedColumnName = "est_id")
    private Estado estado;
}
