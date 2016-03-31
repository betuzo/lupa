package com.codigoartesanal.lupa.services;

/**
 * Created by betuzo on 21/01/16.
 */
public enum OriginPhoto {
    INGRESO,
    EGRESO,
    PERSONA;

    public String getPathPhotoBase() {
        return "photo/";
    }

    public String getPathBase() {
        switch(this) {
            case PERSONA:
                return "persona/";
            case INGRESO:
                return "ingreso/";
            case EGRESO:
                return "egreso/";
            default:
                return null;
        }
    }

    public String getPathDefault() {
        switch(this) {
            case PERSONA:
                return "photo/persona/persona-default.png";
            case INGRESO:
                return "photo/ingreso/ingreso-default.png";
            case EGRESO:
                return "photo/egreso/egreso-default.png";
            default:
                return null;
        }
    }
}
