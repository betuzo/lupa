package com.codigoartesanal.lupa.model.view;

import javax.persistence.*;

/**
 * Created by betuzo on 26/02/16.
 */
@Entity
@Table(	name = "EGRESOS_TOTALES_VIEW" )
public class EgresoTotal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Double totalPendientes;

    @Column(nullable = false)
    private Double totalMontoPendiente;

    @Column(nullable = false)
    private Double totalMonto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalPendientes() {
        return totalPendientes;
    }

    public void setTotalPendientes(Double totalPendientes) {
        this.totalPendientes = totalPendientes;
    }

    public Double getTotalMontoPendiente() {
        return totalMontoPendiente;
    }

    public void setTotalMontoPendiente(Double totalMontoPendiente) {
        this.totalMontoPendiente = totalMontoPendiente;
    }

    public Double getTotalMonto() {
        return totalMonto;
    }

    public void setTotalMonto(Double totalMonto) {
        this.totalMonto = totalMonto;
    }
}
