package com.coagronet.kardexItem;

import com.coagronet.kardex.Kardex;
import com.coagronet.productoPresentacion.ProductoPresentacion;
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
@Table(name = "kardex_item")
public class KardexItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kardex_item_generator")
    @SequenceGenerator(name = "kardex_item_generator", sequenceName = "kardex_item_kai_id_seq", allocationSize = 1)
    @Column(name = "kai_id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "kai_kardex_id", referencedColumnName = "kar_id")
    private Kardex kardex;

    @ManyToOne
    @JoinColumn(name = "kai_producto_presentacion_id", referencedColumnName = "prp_id")
    private ProductoPresentacion productoPresentacion;

    @Column(name = "kai_cantidad")
    private Double cantidad;

    @Column(name = "kai_precio")
    private Double precio;

    @Column(name = "kai_estado", columnDefinition = "integer default 1")
    private Integer estado;

}
