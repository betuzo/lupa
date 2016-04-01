package com.codigoartesanal.lupa.controller;

import com.codigoartesanal.lupa.services.EgresoTokenService;
import com.codigoartesanal.lupa.services.IngresoTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by betuzo on 18/02/16.
 */
@Controller
@RequestMapping("/egresotoken")
public class EgresoTokenController {

    @Autowired
    EgresoTokenService egresoTokenService;

    @ResponseBody
    @RequestMapping(
            value = { "/{token}" },
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> obtenerEgresoTokenByToken(@PathVariable("token") String token) {
        Map<String, Object> response = egresoTokenService.egresoTokenById(token);
        return response;
    }


    @ResponseBody
    @RequestMapping(
            value = { "/{token}" },
            method = {RequestMethod.DELETE},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> deleteEgreso(@PathVariable("token") String token) {
        Map<String, Object> response = new HashMap<>();
        response.put(EgresoTokenService.PROPERTY_TOKEN, token);
        egresoTokenService.deleteEgresoToken(token);
        return response;
    }

}
