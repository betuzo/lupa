package com.codigoartesanal.lupa.services;

import java.util.Map;

/**
 * Created by betuzo on 26/02/16.
 */
public interface ValidarIngresoTokenService {

    String PROPERTY_TOKEN               = "token";
    String PROPERTY_INGRESO_ID          = "ingresoId";
    String PROPERTY_FECHA_VIGENCIA      = "fechaVigencia";

    Map<String,Object> userTokenById(String token);
}
