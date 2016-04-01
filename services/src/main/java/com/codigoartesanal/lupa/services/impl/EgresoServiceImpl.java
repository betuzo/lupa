package com.codigoartesanal.lupa.services.impl;

import com.codigoartesanal.lupa.exception.DeleteException;
import com.codigoartesanal.lupa.model.*;
import com.codigoartesanal.lupa.repositories.EgresoRepository;
import com.codigoartesanal.lupa.repositories.EgresoTokenRepository;
import com.codigoartesanal.lupa.repositories.EventoRepository;
import com.codigoartesanal.lupa.repositories.PersonaRepository;
import com.codigoartesanal.lupa.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by betuzo on 31/03/16.
 */
@Service
public class EgresoServiceImpl implements EgresoService {

    @Autowired
    PathWebService pathWebService;

    @Autowired
    MailService mailService;

    @Autowired
    EgresoRepository egresoRepository;

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    EgresoTokenRepository egresoTokenRepository;

    @Override
    public Map<String, Object> createEgreso(Map<String, String> egresoMap, User user) {
        egresoMap.put(PROPERTY_ENABLED, StatusIngreso.REGISTRADA.name());
        egresoMap.put(PROPERTY_FECHA_REGISTRO, String.valueOf((new Date()).getTime()));
        Egreso egreso = convertMapToEgreso(egresoMap);
        egreso.setRecaudador(personaRepository.findByUsername(user.getUsername()));
        egreso.setEvento(eventoRepository.findOne(egreso.getEvento().getId()));
        egreso = egresoRepository.save(egreso);
        sendTokenForValidation(egreso, egresoMap.get(GeneralService.PROPERTY_CONTEXT));
        return convertEgresoToMap(egreso);
    }

    @Override
    public List<Map<String, Object>> listEgresos() {
        Iterator<Egreso> itEgreso = egresoRepository.findAll().iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itEgreso.hasNext()) {
            Egreso egreso = itEgreso.next();
            Map<String, Object> dto = convertEgresoToMap(egreso);
            copy.add(dto);
        }
        return copy;
    }

    @Override
    public Map<String, Object> findEgresoById(Long idEgreso) {
        return convertEgresoToMap(egresoRepository.findOne(idEgreso));
    }

    @Override
    public void deleteEgreso(Long idEgreso) {
        Egreso egreso = egresoRepository.findOne(idEgreso);
        if (egreso.getStatus() == StatusEgreso.REGISTRADA) {
            egresoTokenRepository.delete(egresoTokenRepository.findAllByEgreso(egreso));
            egresoRepository.delete(idEgreso);
        } else {
            throw new DeleteException("El egreso ya esta validado, contacte al supervisor");
        }

    }

    @Override
    public void updateFacturaByEgreso(String factura, Long idEgreso) {
        egresoRepository.updateFacturaByIdEgreso(factura, idEgreso);
    }

    @Override
    public void updateStatusByEgreso(StatusEgreso statusEgreso, Long idEgreso) {
        egresoRepository.updateStatusByEgreso(statusEgreso, idEgreso);
    }

    private void sendTokenForValidation(Egreso egreso, String context){
        egresoTokenRepository.delete(egresoTokenRepository.findAllByEgreso(egreso));
        EgresoToken egresoToken = egresoTokenRepository.save(generateEgresoToken(egreso));
        sendMailsToken(egresoToken, context);
    }

    private EgresoToken generateEgresoToken(Egreso egreso){
        EgresoToken egresoToken = new EgresoToken();
        egresoToken.setEgreso(egreso);
        Calendar fechaVigencia = Calendar.getInstance();
        fechaVigencia.add(Calendar.DAY_OF_MONTH, PROPERTY_TOKEN_VIGENCIA_DAYS);
        egresoToken.setFechaVigencia(fechaVigencia.getTime());
        egresoToken.setToken(UUID.randomUUID().toString());

        return egresoToken;
    }

    private Map<String, Object> convertEgresoToMap(Egreso egreso) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, egreso.getId());
        map.put(PROPERTY_RECAUDADOR_ID, egreso.getRecaudador().getId());
        map.put(PROPERTY_RECAUDADOR_NOMBRE, egreso.getRecaudador().getNombreCompleto());
        map.put(PROPERTY_EVENTO_ID, egreso.getEvento().getId());
        map.put(PROPERTY_EVENTO_NOMBRE, egreso.getEvento().getNombre());
        map.put(PROPERTY_MONTO, egreso.getMonto());
        map.put(PROPERTY_COMENTARIO, egreso.getComentario());
        map.put(PROPERTY_FICHA_PAGO, egreso.getFactura());
        String pathWebFull = pathWebService.getValidPathWebFoto(egreso.getFactura(), OriginPhoto.EGRESO);
        map.put(PROPERTY_RUTA_FICHA_PAGO, pathWebFull);
        map.put(PROPERTY_HAS_FICHA_PAGO, !pathWebFull.contains(OriginPhoto.EGRESO.getPathDefault()));
        map.put(PROPERTY_FECHA_REGISTRO, egreso.getFechaRegistro());
        map.put(PROPERTY_ENABLED, egreso.getStatus());
        map.put(PROPERTY_ENABLED_DES, egreso.getStatus().getDescription());

        return map;
    }

    private Egreso convertMapToEgreso(Map<String, String> egresoMap) {
        Egreso egreso = new Egreso();
        if (egresoMap.containsKey(PROPERTY_ID)) {
            egreso = this.get(Long.valueOf(egresoMap.get(PROPERTY_ID)));
        }
        Evento evento = new Evento();
        evento.setId(Long.valueOf(egresoMap.get(PROPERTY_EVENTO_ID)));
        egreso.setEvento(evento);
        egreso.setMonto(Double.parseDouble(egresoMap.get(PROPERTY_MONTO)));
        egreso.setComentario(egresoMap.get(PROPERTY_COMENTARIO));
        egreso.setStatus(StatusEgreso.valueOf(egresoMap.get(PROPERTY_ENABLED)));
        egreso.setFechaRegistro(new Date(Long.valueOf(egresoMap.get(PROPERTY_FECHA_REGISTRO))));

        return egreso;
    }

    private Egreso get(Long idEgreso){
        return this.egresoRepository.findOne(idEgreso);
    }

    private void sendMailsToken(EgresoToken egresoToken, String context) {
        Map<String, Object> props = new HashMap<>();
        props.put("folio", egresoToken.getEgreso().getId());
        props.put("link", context + "/#token/egreso/" + egresoToken.getToken());

        mailService.sendValidTokenIngresoByRole("VALIDADOR", props);
    }
}
