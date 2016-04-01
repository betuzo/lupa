package com.codigoartesanal.lupa.services;

import java.util.Map;

/**
 * Created by betuzo on 26/02/16.
 */
public interface EgresoTokenService {

    String PROPERTY_TOKEN               = "token";
    String PROPERTY_INGRESO_ID          = "egresoId";
    String PROPERTY_FECHA_VIGENCIA      = "fechaVigencia";

    Map<String,Object> egresoTokenById(String token);

    void deleteEgresoToken(String token);
}
