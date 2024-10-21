package com.coagronet.tipoProduccion;

import com.coagronet.estado.Estado;
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
@Table(name = "tipo_produccion")
public class TipoProduccion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_produccion_generator")
    @SequenceGenerator(name = "tipo_produccion_generator", sequenceName = "tipo_produccion_tip_id_seq", allocationSize = 1)
    @Column(name = "tip_id", nullable = false)
    private Integer id;

    @Column(name = "tip_nombre", length = 100)
    private String nombre;

    @Column(name = "tip_descripcion", length = 255)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "tip_estado", referencedColumnName = "est_id")
    private Estado estado;

}
