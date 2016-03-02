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
    public Map<String, Object> createPersona(@RequestBody Map<String, String> persona, User user) {
        return personaService.createPersona(persona, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{persona}" },
            method = {RequestMethod.PUT},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> updatePersona(@RequestBody Map<String, String> persona, User user) {
        return personaService.createPersona(persona, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listPersonaByUser(User user) {
        return personaService.listPersonaByAdmin(user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/role/{role}" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listPersonaByRole(@PathVariable("role") String role) {
        return personaService.listPersonaByRole(role);
    }


    @ResponseBody
    @RequestMapping(
            value = { "/{persona}" },
            method = {RequestMethod.DELETE},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> deleteLiga(@PathVariable("persona") Long idPersona, User user) {
        Map<String, Object> response = new HashMap<>();
        response.put(PersonaService.PROPERTY_ID, idPersona);
        DeleteStatusEnum result = personaService.deletePersona(idPersona);
        if (result == DeleteStatusEnum.OK) {
            response.put(GeneralService.PROPERTY_RESULT, true);
            response.put(GeneralService.PROPERTY_MESSAGE, "Persona eliminado");
        } else {
            response.put(GeneralService.PROPERTY_RESULT, false);
            response.put(GeneralService.PROPERTY_MESSAGE,
                    "El persona no se puede eliminar, ya participa en torneo(s)");
        }
        return response;
    }
}
