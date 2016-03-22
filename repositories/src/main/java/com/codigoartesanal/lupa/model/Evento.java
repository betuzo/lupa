package com.codigoartesanal.lupa.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by betuzo on 20/03/16.
 */
@Entity
@Table(	name = "EVENTO" )
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="evento_id_seq")
    @SequenceGenerator(name="evento_id_seq", sequenceName="evento_id_seq")
    private Long id;
    private String nombre;
    private String descripcion;
    @Column(name = "fecha_evento")
    private Date fechaEvento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }
}
