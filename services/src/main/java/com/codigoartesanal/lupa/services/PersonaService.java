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
    String PROPERTY_HAS_FOTO_PERSONA    = "hasFotoPersona";
    String PROPERTY_FOTO_PERSONA        = "fotoPersona";
    String PROPERTY_RUTA_FOTO_PERSONA   = "rutaFotoPersona";

    Map<String,Object> createPersona(Map<String, String> jugador, User user);

    DeleteStatusEnum deletePersona(Long idJugador);

    List<Map<String,Object>> listPersonaByAdmin(User user);

    void updateFotoByPersona(String foto, Long idJugador);

    List<Map<String,Object>> listPersonaByRole(String role);
}
