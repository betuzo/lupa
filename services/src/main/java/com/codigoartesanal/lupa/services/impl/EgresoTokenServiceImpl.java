package com.codigoartesanal.lupa.services.impl;

import com.codigoartesanal.lupa.exception.DeleteException;
import com.codigoartesanal.lupa.exception.TokenException;
import com.codigoartesanal.lupa.model.EgresoToken;
import com.codigoartesanal.lupa.model.StatusEgreso;
import com.codigoartesanal.lupa.repositories.EgresoTokenRepository;
import com.codigoartesanal.lupa.services.EgresoService;
import com.codigoartesanal.lupa.services.EgresoTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by betuzo on 8/03/16.
 */
@Service
public class EgresoTokenServiceImpl implements EgresoTokenService {

    @Autowired
    EgresoService egresoService;

    @Autowired
    EgresoTokenRepository egresoTokenRepository;

    @Override
    public Map<String, Object> egresoTokenById(String token) {
        EgresoToken egresoToken = egresoTokenRepository.findOne(token);
        if (egresoToken == null) {
            throw new TokenException("El token del egreso no existe");
        }
        Map<String, Object> result = egresoService.findEgresoById(egresoToken.getEgreso().getId());
        result.putAll(convertEgresoTokenToMap(egresoToken));
        return result;
    }

    @Override
    public void deleteEgresoToken(String token) {
        EgresoToken egresoToken = egresoTokenRepository.findOne(token);
        if (egresoToken != null) {
            Set<EgresoToken> tokens = egresoTokenRepository.findAllByEgreso(egresoToken.getEgreso());
            if (tokens.size()==1) {
                egresoService.updateStatusByEgreso(StatusEgreso.VALIDA, egresoToken.getEgreso().getId());
            }
            egresoTokenRepository.delete(token);
        } else {
            throw new DeleteException("El egreso ya esta validado, contacte al supervisor");
        }
    }

    private Map<String, Object> convertEgresoTokenToMap(EgresoToken egresoToken) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_TOKEN, egresoToken.getToken());
        map.put(PROPERTY_INGRESO_ID, egresoToken.getEgreso().getId());
        map.put(PROPERTY_FECHA_VIGENCIA, egresoToken.getFechaVigencia());
        return map;
    }
}
