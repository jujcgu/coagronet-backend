package com.coagronet.persona;

import java.util.Date;
import java.util.List;

import com.coagronet.tipoIdentificacion.TipoIdentificacion;
import com.coagronet.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "per_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "per_tipo_identificacion")
    private TipoIdentificacion tipoIdentificacion;

    @Column(name = "per_identificacion")
    private String identificacion;

    @Column(name = "per_apellido")
    private String apellido;

    @Column(name = "per_nombre")
    private String nombre;

    @Column(name = "per_genero")
    private Boolean genero;

    @Column(name = "per_fecha_nacimiento")
    private Date fechaNacimiento;

    @Column(name = "per_estrato")
    private Integer estrato;

    @Column(name = "per_direccion")
    private String direccion;

    @Column(name = "per_celular")
    private String celular;

    @Column(name = "per_estado", columnDefinition = "integer default 1")
    private Integer estado;

    @OneToMany(mappedBy = "persona")
    private List<User> users;

}