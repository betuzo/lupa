package com.codigoartesanal.lupa.services.impl;

import com.codigoartesanal.lupa.exception.DeleteException;
import com.codigoartesanal.lupa.model.Evento;
import com.codigoartesanal.lupa.model.User;
import com.codigoartesanal.lupa.repositories.EventoRepository;
import com.codigoartesanal.lupa.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by betuzo on 20/03/16.
 */
public class EventoServiceImpl implements EventoService {

    @Autowired
    EventoRepository eventoRepository;

    @Override
    public Map<String, Object> createEvento(Map<String, String> eventoMap, User user) {
        Evento evento = convertMapToEvento(eventoMap);
        return convertEventoToMap(evento);
    }

    @Override
    public List<Map<String, Object>> listEventos() {
        Iterator<Evento> itEvento = eventoRepository.findAll().iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itEvento.hasNext()) {
            Evento evento = itEvento.next();
            Map<String, Object> dto = convertEventoToMap(evento);
            copy.add(dto);
        }
        return copy;
    }

    @Override
    public void deleteEvento(Long idEvento) {
        eventoRepository.delete(idEvento);
    }

    private Evento get(Long idEvento){
        return this.eventoRepository.findOne(idEvento);
    }

    private Evento convertMapToEvento(Map<String, String> eventoMap) {
        Evento evento = new Evento();
        if (eventoMap.containsKey(PROPERTY_ID)) {
            evento = this.get(Long.valueOf(eventoMap.get(PROPERTY_ID)));
        }
        evento.setNombre(eventoMap.get(PROPERTY_EVENTO_NOMBRE));
        evento.setDescripcion(eventoMap.get(PROPERTY_EVENTO_DESCRIPCION));
        evento.setFechaEvento(new Date(Long.valueOf(eventoMap.get(PROPERTY_FECHA_EVENTO))));

        return evento;
    }

    private Map<String, Object> convertEventoToMap(Evento evento) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, evento.getId());
        map.put(PROPERTY_EVENTO_NOMBRE, evento.getNombre());
        map.put(PROPERTY_EVENTO_DESCRIPCION, evento.getDescripcion());
        map.put(PROPERTY_FECHA_EVENTO, evento.getFechaEvento());

        return map;
    }
}
