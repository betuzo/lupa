package com.codigoartesanal.lupa.services;

import com.codigoartesanal.lupa.model.User;
import com.codigoartesanal.lupa.services.impl.DeleteStatusEnum;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 22/05/15.
 */
public interface PersonaService {

    String PROPERTY_ID                  = "id";
    String PROPERTY_NOMBRE              = "nombre";
    String PROPERTY_PATERNO             = "paterno";
    String PROPERTY_MATERNO             = "materno";
    String PROPERTY_SEXO                = "sexo";
    String PROPERTY_FECHA_REGISTRO      = "fechaRegistro";
    String PROPERTY_HAS_LOGO_JUGADOR    = "hasLogoJugador";
    String PROPERTY_LOGO_JUGADOR        = "logoJugador";
    String PROPERTY_RUTA_LOGO_JUGADOR   = "rutaLogoJugador";

    Map<String,Object> createJugador(Map<String, String> jugador, User user);

    DeleteStatusEnum deleteJugador(Long idJugador);

    List<Map<String,Object>> listJugadorByAdmin(User user);

    void updateFotoByJugador(String foto, Long idJugador);

}
