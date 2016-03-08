package com.codigoartesanal.lupa.controller;

import com.codigoartesanal.lupa.services.IngresoTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by betuzo on 25/05/15.
 */
@Controller
@RequestMapping("/ingresototal")
public class IngresoTotalController {

    @Autowired
    IngresoTotalService ingresoTotalService;

    @RequestMapping(
            value = { "/{ingresototal}" },
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map<String, Object> listTipoVisibilidad(@PathVariable("ingresototal") Long idIngresoTotal) {
        return ingresoTotalService.findIngresoTotalById(idIngresoTotal);
    }
}
