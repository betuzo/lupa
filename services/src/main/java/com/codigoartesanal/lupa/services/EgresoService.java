package com.codigoartesanal.lupa.services;

import com.codigoartesanal.lupa.model.StatusEgreso;
import com.codigoartesanal.lupa.model.User;
import com.codigoartesanal.lupa.model.dto.GraficaDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 31/03/16.
 */
public interface EgresoService {
    int PROPERTY_TOKEN_VIGENCIA_DAYS    = 5;

    String PROPERTY_ID                  = "id";
    String PROPERTY_RECAUDADOR_ID       = "recaudadorId";
    String PROPERTY_RECAUDADOR_NOMBRE   = "recaudadorNombre";
    String PROPERTY_EVENTO_ID           = "eventoId";
    String PROPERTY_EVENTO_NOMBRE       = "eventoNombre";
    String PROPERTY_MONTO               = "monto";
    String PROPERTY_COMENTARIO          = "comentario";
    String PROPERTY_FICHA_PAGO          = "factura";
    String PROPERTY_HAS_FICHA_PAGO      = "hasFactura";
    String PROPERTY_RUTA_FICHA_PAGO     = "rutaFactura";
    String PROPERTY_FECHA_REGISTRO      = "fechaRegistro";
    String PROPERTY_ENABLED             = "enabled";
    String PROPERTY_ENABLED_DES         = "enabledDes";

    Map<String,Object> createEgreso(Map<String, String> egreso, User user);

    List<Map<String,Object>> listEgresos();

    List<GraficaDTO> listEgresosDetail();

    Map<String,Object> findEgresoById(Long idEgreso);

    void deleteEgreso(Long idEgreso);

    void updateFacturaByEgreso(String factura, Long idEgreso);

    void updateStatusByEgreso(StatusEgreso statusEgreso, Long idEgreso);
}
