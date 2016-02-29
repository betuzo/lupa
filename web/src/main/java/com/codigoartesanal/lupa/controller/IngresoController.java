package com.codigoartesanal.lupa.controller;

import com.codigoartesanal.lupa.model.User;
import com.codigoartesanal.lupa.services.IngresoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 26/02/16.
 */
@Controller
@RequestMapping("/ingreso")
public class IngresoController {

    @Autowired
    IngresoService ingresoService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createIngreso(@RequestBody Map<String, String> ingreso, User user) {
        return ingresoService.createIngreso(ingreso, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listIngresos() {
        return ingresoService.listIngresos();
    }

}
