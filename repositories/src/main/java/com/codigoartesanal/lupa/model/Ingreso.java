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

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = true)
    private Evento evento;

    @Column(nullable = false)
    private Double monto;

    @Column(nullable = true)
    private String comentario;

    @Column(name = "ficha_pago", nullable = true)
    private String fichaPago;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoVisibilidad visibilidad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusIngreso status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Persona getDonador() {
        return donador;
    }

    public void setDonador(Persona donador) {
        this.donador = donador;
    }

    public Persona getRecaudador() {
        return recaudador;
    }

    public void setRecaudador(Persona recaudador) {
        this.recaudador = recaudador;
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

    public String getFichaPago() {
        return fichaPago;
    }

    public void setFichaPago(String fichaPago) {
        this.fichaPago = fichaPago;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public TipoVisibilidad getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(TipoVisibilidad visibilidad) {
        this.visibilidad = visibilidad;
    }

    public StatusIngreso getStatus() {
        return status;
    }

    public void setStatus(StatusIngreso status) {
        this.status = status;
    }
}
