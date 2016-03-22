package com.codigoartesanal.lupa.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by betuzo on 26/02/16.
 */
@Entity
@Table(	name = "EGRESO" )
public class Egreso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="egreso_id_seq")
    @SequenceGenerator(name="egreso_id_seq", sequenceName="egreso_id_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recaudador_id", nullable = false)
    private Persona recaudador;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = true)
    private Evento evento;

    @Column(nullable = false)
    private Double monto;

    @Column(nullable = true)
    private String comentario;

    @Column(name = "factura", nullable = true)
    private String factura;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEgreso status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Persona getRecaudador() {
        return recaudador;
    }

    public void setRecaudador(Persona recaudador) {
        this.recaudador = recaudador;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public StatusEgreso getStatus() {
        return status;
    }

    public void setStatus(StatusEgreso status) {
        this.status = status;
    }
}
