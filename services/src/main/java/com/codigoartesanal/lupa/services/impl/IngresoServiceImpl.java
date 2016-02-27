package com.codigoartesanal.lupa.services.impl;

import com.codigoartesanal.lupa.model.Ingreso;
import com.codigoartesanal.lupa.model.Persona;
import com.codigoartesanal.lupa.model.TipoVisibilidad;
import com.codigoartesanal.lupa.model.User;
import com.codigoartesanal.lupa.repositories.IngresoRepository;
import com.codigoartesanal.lupa.repositories.PersonaRepository;
import com.codigoartesanal.lupa.services.IngresoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by betuzo on 26/02/16.
 */
@Service
public class IngresoServiceImpl implements IngresoService {

    @Autowired
    IngresoRepository ingresoRepository;

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public Map<String, Object> createIngreso(Map<String, String> ingresoMap, User user) {
        Ingreso ingreso = convertMapToIngreso(ingresoMap);
        ingreso.setRecaudador(personaRepository.findByUsername(user.getUsername()));
        return convertIngresoToMap(ingresoRepository.save(ingreso));
    }

    private Map<String, Object> convertIngresoToMap(Ingreso ingreso) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, ingreso.getId());
        map.put(PROPERTY_DONADOR_ID, ingreso.getDonador().getId());
        map.put(PROPERTY_DONADOR_NOMBRE, ingreso.getDonador().getNombre());
        map.put(PROPERTY_RECAUDADOR_ID, ingreso.getRecaudador().getId());
        map.put(PROPERTY_RECAUDADOR_NOMBRE, ingreso.getRecaudador().getNombre());
        map.put(PROPERTY_MONTO, ingreso.getMonto());
        map.put(PROPERTY_FECHA_REGISTRO, ingreso.getFechaRegistro());
        map.put(PROPERTY_VISIBILIDAD, ingreso.getVisibilidad());
        map.put(PROPERTY_ENABLED, ingreso.isEnabled());

        return map;
    }

    private Ingreso convertMapToIngreso(Map<String, String> ingresoMap) {
        Ingreso ingreso = new Ingreso();
        if (ingresoMap.containsKey(PROPERTY_ID)) {
            ingreso = this.get(Long.valueOf(ingresoMap.get(PROPERTY_ID)));
        }
        Persona donador = new Persona();
        donador.setId(Long.valueOf(ingresoMap.get(PROPERTY_DONADOR_ID)));
        ingreso.setDonador(donador);
        ingreso.setMonto(Double.parseDouble(ingresoMap.get(PROPERTY_MONTO)));
        ingreso.setVisibilidad(TipoVisibilidad.valueOf(ingresoMap.get(PROPERTY_VISIBILIDAD)));
        ingreso.setEnabled(Boolean.parseBoolean(ingresoMap.get(PROPERTY_ENABLED)));
        ingreso.setFechaRegistro(new Date(Long.valueOf(ingresoMap.get(PROPERTY_FECHA_REGISTRO))));

        return ingreso;
    }

    private Ingreso get(Long idIngreso){
        return this.ingresoRepository.findOne(idIngreso);
    }
}
