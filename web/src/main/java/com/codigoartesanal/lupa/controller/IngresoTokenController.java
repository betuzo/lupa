package com.codigoartesanal.lupa.controller;

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
@RequestMapping("/ingresotoken")
public class IngresoTokenController {

    @Autowired
    IngresoTokenService ingresoTokenService;

    @ResponseBody
    @RequestMapping(
            value = { "/{token}" },
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> obtenerUserTokenByToken(@PathVariable("token") String token) {
        Map<String, Object> response = ingresoTokenService.ingresoTokenById(token);
        return response;
    }


    @ResponseBody
    @RequestMapping(
            value = { "/{token}" },
            method = {RequestMethod.DELETE},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> deleteIngreso(@PathVariable("token") String token) {
        Map<String, Object> response = new HashMap<>();
        response.put(IngresoTokenService.PROPERTY_TOKEN, token);
        ingresoTokenService.deleteIngresoToken(token);
        return response;
    }

}
