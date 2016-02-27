package com.codigoartesanal.lupa.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by betuzo on 25/01/16.
 */
@Entity
@Table(	name = "VALIDAR_INGRESO_TOKEN" )
public class ValidarIngresoToken {
    @Id
    @Column(name = "token", nullable = false, length = 45)
    private String token;
    @OneToOne
    @JoinColumn(name = "ingreso_id", nullable = false)
    private Ingreso ingreso;
    @Column(name = "fecha_vigencia")
    private Date fechaVigencia;

    public Ingreso getIngreso() {
        return ingreso;
    }

    public void setIngreso(Ingreso ingreso) {
        this.ingreso = ingreso;
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

    public ValidarIngresoToken() {
    }

    public ValidarIngresoToken(User user, String token, TipoToken tipo, Date fechaVigencia) {
        this.token = token;
        this.ingreso = ingreso;
        this.fechaVigencia = fechaVigencia;
    }
}
