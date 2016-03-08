package com.codigoartesanal.lupa.services.impl;

import com.codigoartesanal.lupa.model.view.IngresoTotal;
import com.codigoartesanal.lupa.repositories.IngresoTotalRepository;
import com.codigoartesanal.lupa.services.IngresoTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by betuzo on 8/03/16.
 */
@Service
public class IngresoTotalServiceImpl implements IngresoTotalService {

    @Autowired
    IngresoTotalRepository ingresoTotalRepository;

    @Override
    public Map<String, Object> findIngresoTotalById(Long idIngresoTotal) {
        return convertIngresoTotalToMap(ingresoTotalRepository.findOne(idIngresoTotal));
    }

    private Map<String, Object> convertIngresoTotalToMap(IngresoTotal ingresoTotal) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, ingresoTotal.getId());
        map.put(PROPERTY_TOTAL_PENDIENTES, ingresoTotal.getTotalPendientes());
        map.put(PROPERTY_TOTAL_MONTO_PENDIENTES, ingresoTotal.getTotalMontoPendiente());
        map.put(PROPERTY_TOTAL_MONTO, ingresoTotal.getTotalMonto());
        return map;
    }
}
