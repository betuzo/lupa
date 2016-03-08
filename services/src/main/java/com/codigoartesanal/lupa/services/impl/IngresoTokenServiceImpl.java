package com.codigoartesanal.lupa.services.impl;

import com.codigoartesanal.lupa.exception.DeleteException;
import com.codigoartesanal.lupa.model.StatusIngreso;
import com.codigoartesanal.lupa.model.ValidarIngresoToken;
import com.codigoartesanal.lupa.repositories.ValidarIngresoTokenRepository;
import com.codigoartesanal.lupa.services.IngresoService;
import com.codigoartesanal.lupa.services.IngresoTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by betuzo on 8/03/16.
 */
@Service
public class IngresoTokenServiceImpl implements IngresoTokenService {

    @Autowired
    IngresoService ingresoService;

    @Autowired
    ValidarIngresoTokenRepository validarIngresoTokenRepository;

    @Override
    public Map<String, Object> ingresoTokenById(String token) {
        ValidarIngresoToken ingresoToken = validarIngresoTokenRepository.findOne(token);
        Map<String, Object> result = ingresoService.findIngresoById(ingresoToken.getIngreso().getId());
        result.putAll(convertIngresoTokenToMap(ingresoToken));
        return result;
    }

    @Override
    public void deleteIngresoToken(String token) {
        ValidarIngresoToken ingresoToken = validarIngresoTokenRepository.findOne(token);
        if (ingresoToken != null) {
            ingresoService.updateStatusByIngreso(StatusIngreso.VALIDA.name(), ingresoToken.getIngreso().getId());
            validarIngresoTokenRepository.delete(token);
        } else {
            throw new DeleteException("El ingreso ya esta validado, contacte al supervisor");
        }
    }

    private Map<String, Object> convertIngresoTokenToMap(ValidarIngresoToken ingresoToken) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_TOKEN, ingresoToken.getToken());
        map.put(PROPERTY_INGRESO_ID, ingresoToken.getIngreso().getId());
        map.put(PROPERTY_FECHA_VIGENCIA, ingresoToken.getFechaVigencia());
        return map;
    }
}
