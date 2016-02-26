package com.codigoartesanal.lupa.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by betuzo on 26/02/16.
 */
@Entity
@Table(	name = "INGRESO" )
public class Ingreso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ingreso_id_seq")
    @SequenceGenerator(name="ingreso_id_seq", sequenceName="ingreso_id_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "donador_id", nullable = false)
    private Persona donador;

    @ManyToOne
    @JoinColumn(name = "recaudador_id", nullable = false)
    private Persona recaudador;

    @Column(nullable = false)
    private Double monto;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoVisibilidad visibilidad;

    @Column(nullable = false)
    private boolean enabled;
}
