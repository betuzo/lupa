package com.codigoartesanal.lupa.model;

/**
 * Created by betuzo on 26/02/16.
 */
public enum TipoVisibilidad {
    ANONIMA,PUBLICA;

    public String getDescription() {
        switch(this) {
            case ANONIMA:
                return "Donación anonima";
            case PUBLICA:
                return "Donación pública";
            default:
                return null;
        }
    }
}
