package com.coagronet.tipoEspacio;

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
@Table(name = "tipo_espacio")
public class TipoEspacio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_espacio_generator")
    @SequenceGenerator(name = "tipo_espacio_generator", sequenceName = "tipo_espacio_tie_id_seq", allocationSize = 1)
    @Column(name = "tie_id", nullable = false)
    private Integer id;

    @Column(name = "tie_nombre", length = 100)
    private String nombre;

    @Column(name = "tie_descripcion", length = 255)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "tie_estado", referencedColumnName = "est_id")
    private Estado estado;
}
