package com.codigoartesanal.lupa.services.impl;

import com.codigoartesanal.lupa.model.view.EgresoTotal;
import com.codigoartesanal.lupa.model.view.IngresoTotal;
import com.codigoartesanal.lupa.repositories.EgresoTotalRepository;
import com.codigoartesanal.lupa.repositories.IngresoTotalRepository;
import com.codigoartesanal.lupa.services.EgresoTotalService;
import com.codigoartesanal.lupa.services.IngresoTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by betuzo on 8/03/16.
 */
@Service
public class EgresoTotalServiceImpl implements EgresoTotalService {

    @Autowired
    EgresoTotalRepository egresoTotalRepository;

    @Override
    public Map<String, Object> findEgresoTotalById(Long idEgresoTotal) {
        return convertEgresoTotalToMap(egresoTotalRepository.findOne(idEgresoTotal));
    }

    private Map<String, Object> convertEgresoTotalToMap(EgresoTotal egresoTotal) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, egresoTotal.getId());
        map.put(PROPERTY_TOTAL_PENDIENTES, egresoTotal.getTotalPendientes());
        map.put(PROPERTY_TOTAL_MONTO_PENDIENTES, egresoTotal.getTotalMontoPendiente());
        map.put(PROPERTY_TOTAL_MONTO, egresoTotal.getTotalMonto());
        return map;
    }
}
