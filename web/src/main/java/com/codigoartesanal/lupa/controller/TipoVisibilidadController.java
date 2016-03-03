package com.codigoartesanal.lupa.controller;

import com.codigoartesanal.lupa.model.TipoVisibilidad;
import com.codigoartesanal.lupa.services.GeneralService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by betuzo on 25/05/15.
 */
@Controller
@RequestMapping("/tipovisibilidad")
public class TipoVisibilidadController {

    @RequestMapping(
            value = { "" },
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public List<Map<String, String>> listTipoVisibilidad() {
        List<Map<String, String>> tipoVisibilidades = new ArrayList<>();
        for (TipoVisibilidad tipoVisibilidad : TipoVisibilidad.values()) {
            Map<String, String> tipoVisibilidadMap = new HashMap<>();
            tipoVisibilidadMap.put(GeneralService.PROPERTY_CLAVE, tipoVisibilidad.toString());
            tipoVisibilidadMap.put(GeneralService.PROPERTY_DESCRIPCION, tipoVisibilidad.getDescription());
            tipoVisibilidades.add(tipoVisibilidadMap);
        }

        return tipoVisibilidades;
    }
}
