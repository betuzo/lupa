package com.codigoartesanal.lupa.services;

import com.codigoartesanal.lupa.model.StatusIngreso;
import com.codigoartesanal.lupa.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 26/02/16.
 */
public interface IngresoService {
    int PROPERTY_TOKEN_VIGENCIA_DAYS    = 5;

    String PROPERTY_ID                  = "id";
    String PROPERTY_DONADOR_ID          = "donadorId";
    String PROPERTY_DONADOR_NOMBRE      = "donadorNombre";
    String PROPERTY_RECAUDADOR_ID       = "recaudadorId";
    String PROPERTY_RECAUDADOR_NOMBRE   = "recaudadorNombre";
    String PROPERTY_EVENTO_ID           = "eventoId";
    String PROPERTY_EVENTO_NOMBRE       = "eventoNombre";
    String PROPERTY_MONTO               = "monto";
    String PROPERTY_COMENTARIO          = "comentario";
    String PROPERTY_FICHA_PAGO          = "fichaPago";
    String PROPERTY_HAS_FICHA_PAGO      = "hasFichaPago";
    String PROPERTY_RUTA_FICHA_PAGO     = "rutaFichaPago";
    String PROPERTY_FECHA_REGISTRO      = "fechaRegistro";
    String PROPERTY_VISIBILIDAD         = "visibilidad";
    String PROPERTY_VISIBILIDAD_DES     = "visibilidadDes";
    String PROPERTY_ENABLED             = "enabled";
    String PROPERTY_ENABLED_DES         = "enabledDes";

    Map<String,Object> createIngreso(Map<String, String> ingreso, User user);

    List<Map<String,Object>> listIngresos();

    Map<String,Object> findIngresoById(Long idIngreso);

    void deleteIngreso(Long idIngreso);

    void updateFichaPagoByIngreso(String fichaPago, Long idIngreso);

    void updateStatusByIngreso(StatusIngreso statusIngreso, Long id);
}
