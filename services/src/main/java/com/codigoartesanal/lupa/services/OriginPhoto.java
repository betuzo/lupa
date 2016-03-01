package com.codigoartesanal.lupa.services;

/**
 * Created by betuzo on 21/01/16.
 */
public enum OriginPhoto {
    INGRESO,
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
            default:
                return null;
        }
    }
}
