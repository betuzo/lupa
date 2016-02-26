package com.codigoartesanal.lupa.controller;

import com.codigoartesanal.lupa.model.TipoToken;
import com.codigoartesanal.lupa.services.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by betuzo on 18/02/16.
 */
@Controller
@RequestMapping("/usertoken")
public class UserTokenController {

    @Autowired
    UserTokenService userTokenService;

    @ResponseBody
    @RequestMapping(
            value = { "/{token}" },
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> obtenerUserTokenByToken(@PathVariable("token") String token) {
        Map<String, Object> response = userTokenService.userTokenByIdAndTipo(token, TipoToken.VALID_EMAIL);
        return response;
    }

}
