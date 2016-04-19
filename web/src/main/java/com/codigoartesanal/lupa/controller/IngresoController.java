package com.codigoartesanal.lupa.controller;

import com.codigoartesanal.lupa.model.Ingreso;
import com.codigoartesanal.lupa.model.User;
import com.codigoartesanal.lupa.model.UserRole;
import com.codigoartesanal.lupa.model.dto.GraficaDTO;
import com.codigoartesanal.lupa.services.GeneralService;
import com.codigoartesanal.lupa.services.IngresoService;
import com.codigoartesanal.lupa.services.impl.DeleteStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public Map<String, Object> createIngreso(@RequestBody Map<String, String> ingreso, User user, HttpServletRequest request) {
        ingreso.put(GeneralService.PROPERTY_CONTEXT, request.getScheme() + "://" + request.getServerName());
        return ingresoService.createIngreso(ingreso, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "/{ingreso}" },
            method = {RequestMethod.PUT},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> updateIngreso(@RequestBody Map<String, String> ingreso, User user, HttpServletRequest request) {
        ingreso.put(GeneralService.PROPERTY_CONTEXT, request.getScheme() + "://" + request.getServerName());
        return ingresoService.createIngreso(ingreso, user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<Map<String, Object>> listIngresos(User user) {
        return ingresoService.listIngresosByRole(user);
    }


    @ResponseBody
    @RequestMapping(
            value = { "/{ingreso}" },
            method = {RequestMethod.DELETE},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> deleteIngreso(@PathVariable("ingreso") Long idIngreso, User user) {
        Map<String, Object> response = new HashMap<>();
        response.put(IngresoService.PROPERTY_ID, idIngreso);
        ingresoService.deleteIngreso(idIngreso);
        return response;
    }

    @ResponseBody
    @RequestMapping(
            value = { "/detail" },
            method = {RequestMethod.GET},
            produces = {"application/json;charset=UTF-8"})
    public List<GraficaDTO> listIngresosDetail() {
        return ingresoService.listIngresosDetail();
    }

}
