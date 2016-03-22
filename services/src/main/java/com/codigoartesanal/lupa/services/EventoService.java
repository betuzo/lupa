package com.codigoartesanal.lupa.services;

import com.codigoartesanal.lupa.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 26/02/16.
 */
public interface EventoService {

    String PROPERTY_ID                  = "id";
    String PROPERTY_EVENTO_NOMBRE       = "eventoNombre";
    String PROPERTY_EVENTO_DESCRIPCION  = "eventoDescripcion";
    String PROPERTY_FECHA_EVENTO        = "fechaEvento";

    Map<String,Object> createEvento(Map<String, String> eventoMap, User user);

    List<Map<String,Object>> listEventos();

    void deleteEvento(Long idEvento);

}
