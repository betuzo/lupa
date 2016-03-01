package com.codigoartesanal.lupa.model;

/**
 * Created by betuzo on 1/03/16.
 */
public enum StatusIngreso {
    REGISTRADA, VALIDA;

    public String getDescription() {
        switch(this) {
            case REGISTRADA:
                return "Por validar";
            case VALIDA:
                return "Validada";
            default:
                return null;
        }
    }
}
