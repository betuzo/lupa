package com.codigoartesanal.lupa.controller;

import com.codigoartesanal.lupa.services.EgresoTotalService;
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
@RequestMapping("/egresototal")
public class EgresoTotalController {

    @Autowired
    EgresoTotalService egresoTotalService;

    @RequestMapping(
            value = { "/{egresototal}" },
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map<String, Object> listTipoVisibilidad(@PathVariable("egresototal") Long idEgresoTotal) {
        return egresoTotalService.findEgresoTotalById(idEgresoTotal);
    }
}
