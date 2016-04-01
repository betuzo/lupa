package com.codigoartesanal.lupa.services.impl;

import com.codigoartesanal.lupa.exception.DeleteException;
import com.codigoartesanal.lupa.exception.TokenException;
import com.codigoartesanal.lupa.model.StatusIngreso;
import com.codigoartesanal.lupa.model.IngresoToken;
import com.codigoartesanal.lupa.repositories.IngresoTokenRepository;
import com.codigoartesanal.lupa.services.IngresoService;
import com.codigoartesanal.lupa.services.IngresoTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by betuzo on 8/03/16.
 */
@Service
public class IngresoTokenServiceImpl implements IngresoTokenService {

    @Autowired
    IngresoService ingresoService;

    @Autowired
    IngresoTokenRepository ingresoTokenRepository;

    @Override
    public Map<String, Object> ingresoTokenById(String token) {
        IngresoToken ingresoToken = ingresoTokenRepository.findOne(token);
        if (ingresoToken == null) {
            throw new TokenException("El token del ingreso no existe");
        }
        Map<String, Object> result = ingresoService.findIngresoById(ingresoToken.getIngreso().getId());
        result.putAll(convertIngresoTokenToMap(ingresoToken));
        return result;
    }

    @Override
    public void deleteIngresoToken(String token) {
        IngresoToken ingresoToken = ingresoTokenRepository.findOne(token);
        if (ingresoToken != null) {
            Set<IngresoToken> tokens = ingresoTokenRepository.findAllByIngreso(ingresoToken.getIngreso());
            if (tokens.size()==1) {
                ingresoService.updateStatusByIngreso(StatusIngreso.VALIDA, ingresoToken.getIngreso().getId());
            }
            ingresoTokenRepository.delete(token);
        } else {
            throw new DeleteException("El ingreso ya esta validado, contacte al supervisor");
        }
    }

    private Map<String, Object> convertIngresoTokenToMap(IngresoToken ingresoToken) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_TOKEN, ingresoToken.getToken());
        map.put(PROPERTY_INGRESO_ID, ingresoToken.getIngreso().getId());
        map.put(PROPERTY_FECHA_VIGENCIA, ingresoToken.getFechaVigencia());
        return map;
    }
}
