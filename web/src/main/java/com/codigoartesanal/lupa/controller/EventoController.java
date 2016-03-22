package com.codigoartesanal.lupa.controller;

import com.codigoartesanal.lupa.model.User;
import com.codigoartesanal.lupa.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 26/02/16.
 */
@Controller
@RequestMapping("/evento")
public class EventoController {

    @Autowired
    EventoService eventoService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createEvento(@RequestBody Map<String, String> evento, User user, HttpServletRequest request) {
        return eventoService.createEvento(evento, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{evento}" },
            method = {RequestMethod.PUT},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> updateEvento(@RequestBody Map<String, String> evento, User user) {
        return eventoService.createEvento(evento, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listEventos() {
        return eventoService.listEventos();
    }


    @ResponseBody
    @RequestMapping(
            value = { "/{evento}" },
            method = {RequestMethod.DELETE},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> deleteEvento(@PathVariable("evento") Long idEvento, User user) {
        Map<String, Object> response = new HashMap<>();
        response.put(EventoService.PROPERTY_ID, idEvento);
        eventoService.deleteEvento(idEvento);
        return response;
    }

}
