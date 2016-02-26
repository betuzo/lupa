package com.codigoartesanal.lupa.model;

/**
 * Created by betuzo on 15/02/16.
 */
public enum TipoToken {
    VALID_EMAIL, CHANGE_PASSWORD;

    public String getDescription() {
        switch(this) {
            case VALID_EMAIL:
                return "Registro";
            case CHANGE_PASSWORD:
                return "Cambio de password";
            default:
                return null;
        }
    }
}
