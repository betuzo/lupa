package com.codigoartesanal.lupa.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by betuzo on 25/01/16.
 */
@Entity
@Table(	name = "EGRESO_TOKEN" )
public class EgresoToken {
    @Id
    @Column(name = "token", nullable = false, length = 45)
    private String token;
    @OneToOne
    @JoinColumn(name = "egreso_id", nullable = false)
    private Egreso egreso;
    @Column(name = "fecha_vigencia")
    private Date fechaVigencia;

    public Egreso getEgreso() {
        return egreso;
    }

    public void setEgreso(Egreso egreso) {
        this.egreso = egreso;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(Date fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    public EgresoToken() {
    }

    public EgresoToken(String token, Egreso egreso, Date fechaVigencia) {
        this.token = token;
        this.egreso = egreso;
        this.fechaVigencia = fechaVigencia;
    }
}
