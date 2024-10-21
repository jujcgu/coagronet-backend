package com.coagronet.tipoBloque;

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
@Table(name = "tipo_bloque")
public class TipoBloque {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_bloque_generator")
    @SequenceGenerator(name = "tipo_bloque_generator", sequenceName = "tipo_bloque_tib_id_seq", allocationSize = 1)
    @Column(name = "tib_id", nullable = false)
    private Integer id;

    @Column(name = "tib_nombre", length = 100)
    private String nombre;

    @Column(name = "tib_descripcion", length = 255)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "tib_estado", referencedColumnName = "est_id")
    private Estado estado;

}
