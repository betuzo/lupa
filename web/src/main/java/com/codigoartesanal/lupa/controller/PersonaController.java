package com.codigoartesanal.lupa.controller;

import com.codigoartesanal.lupa.model.User;
import com.codigoartesanal.lupa.services.GeneralService;
import com.codigoartesanal.lupa.services.PersonaService;
import com.codigoartesanal.lupa.services.impl.DeleteStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 22/05/15.
 */
@Controller
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createJugador(@RequestBody Map<String, String> jugador, User user) {
        return personaService.createJugador(jugador, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{jugador}" },
            method = {RequestMethod.PUT},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> updateJugador(@RequestBody Map<String, String> jugador, User user) {
        return personaService.createJugador(jugador, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listJugadorByUser(User user) {
        return personaService.listJugadorByAdmin(user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{jugador}" },
            method = {RequestMethod.DELETE},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> deleteLiga(@PathVariable("jugador") Long idJugador, User user) {
        Map<String, Object> response = new HashMap<>();
        response.put(PersonaService.PROPERTY_ID, idJugador);
        DeleteStatusEnum result = personaService.deleteJugador(idJugador);
        if (result == DeleteStatusEnum.OK) {
            response.put(GeneralService.PROPERTY_RESULT, true);
            response.put(GeneralService.PROPERTY_MESSAGE, "Persona eliminado");
        } else {
            response.put(GeneralService.PROPERTY_RESULT, false);
            response.put(GeneralService.PROPERTY_MESSAGE,
                    "El jugador no se puede eliminar, ya participa en torneo(s)");
        }
        return response;
    }
}
