package com.codigoartesanal.lupa.services.impl;

import com.codigoartesanal.lupa.model.*;
import com.codigoartesanal.lupa.repositories.PersonaRepository;
import com.codigoartesanal.lupa.services.PersonaService;
import com.codigoartesanal.lupa.services.OriginPhoto;
import com.codigoartesanal.lupa.services.PathPhoto;
import com.codigoartesanal.lupa.services.PathWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by betuzo on 22/05/15.
 */
@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    PathWebService pathWebService;

    @Override
    public Map<String, Object> createJugador(Map<String, String> jugadorMap, User admin) {
        Persona persona = convertMapToJugador(jugadorMap);
        persona.setAdmin(admin);
        return convertJugadorToMap(personaRepository.save(persona));
    }

    @Override
    public DeleteStatusEnum deleteJugador(Long idJugador) {
        try {
            personaRepository.delete(idJugador);
        } catch (DataIntegrityViolationException exception){
            return DeleteStatusEnum.VIOLATION;
        }
        return DeleteStatusEnum.OK;
    }

    @Override
    public List<Map<String, Object>> listJugadorByAdmin(User user) {
        Iterator<Persona> itJugador = personaRepository.findAllByAdmin(user).iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itJugador.hasNext()) {
            Persona persona = itJugador.next();
            Map<String, Object> dto = convertJugadorToMap(persona);
            copy.add(dto);
        }
        return copy;
    }

    @Override
    public void updateFotoByJugador(String foto, Long idJugador) {
        personaRepository.updateFotoByIdJugador(foto, idJugador);
    }

    private Map<String, Object> convertJugadorToMap(Persona persona) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, persona.getId());
        map.put(PROPERTY_NOMBRE, persona.getNombre());
        map.put(PROPERTY_PATERNO, persona.getPaterno());
        map.put(PROPERTY_MATERNO, persona.getMaterno());
        map.put(PROPERTY_LOGO_JUGADOR, persona.getRutaFoto());
        String pathWebFull = pathWebService.getValidPathWebFoto(persona.getRutaFoto(), OriginPhoto.PERSONA);
        map.put(PROPERTY_RUTA_LOGO_JUGADOR, pathWebFull);
        map.put(PROPERTY_HAS_LOGO_JUGADOR, !pathWebFull.contains(PathPhoto.JUGADOR_DEFAULT.getPath()));
        map.put(PROPERTY_SEXO, persona.getSexo());
        map.put(PROPERTY_FECHA_REGISTRO, persona.getFechaRegistro());

        return map;
    }

    private Persona convertMapToJugador(Map<String, String> ligaMap) {
        Persona persona = new Persona();
        if (ligaMap.containsKey(PROPERTY_ID)) {
            persona = this.get(Long.valueOf(ligaMap.get(PROPERTY_ID)));
        }
        persona.setNombre(ligaMap.get(PROPERTY_NOMBRE));
        persona.setPaterno(ligaMap.get(PROPERTY_PATERNO));
        persona.setMaterno(ligaMap.get(PROPERTY_MATERNO));
        persona.setRutaFoto(ligaMap.get(PROPERTY_LOGO_JUGADOR));
        persona.setSexo(Sexo.valueOf(ligaMap.get(PROPERTY_SEXO)));
        persona.setFechaRegistro(new Date(Long.valueOf(ligaMap.get(PROPERTY_FECHA_REGISTRO))));

        return persona;
    }

    private Persona get(Long idJugador){
        return this.personaRepository.findOne(idJugador);
    }
}
