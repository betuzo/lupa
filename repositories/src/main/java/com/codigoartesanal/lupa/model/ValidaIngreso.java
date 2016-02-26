package com.codigoartesanal.lupa.model;

import javax.persistence.*;

/**
 * Created by betuzo on 26/02/16.
 */
@Entity
@Table(	name = "VALIDA_INGRESO" )
public class ValidaIngreso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="valida_ingreso_id_seq")
    @SequenceGenerator(name="valida_ingreso_id_seq", sequenceName="valida_ingreso_id_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ingreso_id", nullable = false)
    private Ingreso ingreso;

    @ManyToOne
    @JoinColumn(name = "validador_id", nullable = false)
    private Persona validador;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoValidacion tipo;
}
