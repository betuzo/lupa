package com.codigoartesanal.lupa.services;

import java.util.Map;

/**
 * Created by betuzo on 8/03/16.
 */
public interface IngresoTotalService {
    String PROPERTY_ID                          = "id";
    String PROPERTY_TOTAL_PENDIENTES            = "totalPendientes";
    String PROPERTY_TOTAL_MONTO_PENDIENTES      = "totalMontoPendiente";
    String PROPERTY_TOTAL_MONTO                 = "totalMonto";

    Map<String,Object> findIngresoTotalById(Long idIngresoTotal);
}
