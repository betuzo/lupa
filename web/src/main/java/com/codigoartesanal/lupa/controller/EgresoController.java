package com.codigoartesanal.lupa.controller;

import com.codigoartesanal.lupa.model.User;
import com.codigoartesanal.lupa.services.GeneralService;
import com.codigoartesanal.lupa.services.EgresoService;
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
@RequestMapping("/egreso")
public class EgresoController {

    @Autowired
    EgresoService egresoService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createEgreso(@RequestBody Map<String, String> egreso, User user, HttpServletRequest request) {
        egreso.put(GeneralService.PROPERTY_CONTEXT, request.getScheme() + "://" + request.getServerName());
        return egresoService.createEgreso(egreso, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{egreso}" },
            method = {RequestMethod.PUT},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> updateEgreso(@RequestBody Map<String, String> egreso, User user,  HttpServletRequest request) {
        egreso.put(GeneralService.PROPERTY_CONTEXT, request.getScheme() + "://" + request.getServerName());
        return egresoService.createEgreso(egreso, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listEgresos() {
        return egresoService.listEgresos();
    }


    @ResponseBody
    @RequestMapping(
            value = { "/{egreso}" },
            method = {RequestMethod.DELETE},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> deleteEgreso(@PathVariable("egreso") Long idEgreso, User user) {
        Map<String, Object> response = new HashMap<>();
        response.put(EgresoService.PROPERTY_ID, idEgreso);
        egresoService.deleteEgreso(idEgreso);
        return response;
    }

}
