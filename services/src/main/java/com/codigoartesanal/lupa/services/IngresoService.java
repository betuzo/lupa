package com.codigoartesanal.lupa.services;

import com.codigoartesanal.lupa.model.User;

import java.util.Map;

/**
 * Created by betuzo on 26/02/16.
 */
public interface IngresoService {

    String PROPERTY_ID                  = "id";
    String PROPERTY_DONADOR_ID          = "donadorId";
    String PROPERTY_DONADOR_NOMBRE      = "donadorNombre";
    String PROPERTY_RECAUDADOR_ID       = "recaudadorId";
    String PROPERTY_RECAUDADOR_NOMBRE   = "recaudadorNombre";
    String PROPERTY_MONTO               = "monto";
    String PROPERTY_FECHA_REGISTRO      = "fechaRegistro";
    String PROPERTY_VISIBILIDAD         = "visibilidad";
    String PROPERTY_ENABLED             = "enabled";

    Map<String,Object> createIngreso(Map<String, String> ingreso, User user);

}
